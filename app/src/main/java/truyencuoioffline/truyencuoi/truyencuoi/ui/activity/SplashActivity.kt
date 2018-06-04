package truyencuoioffline.truyencuoi.truyencuoi.ui.activity

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import com.squareup.picasso.Picasso
import io.realm.Realm
import io.realm.RealmObject
import kotlinx.android.synthetic.main.activity_splash.*
import truyencuoioffline.truyencuoi.truyencuoi.R
import truyencuoioffline.truyencuoi.truyencuoi.common.ApiConstant
import truyencuoioffline.truyencuoi.truyencuoi.extensions.getHttpService
import truyencuoioffline.truyencuoi.truyencuoi.extensions.isOnline
import truyencuoioffline.truyencuoi.truyencuoi.extensions.loadImage
import truyencuoioffline.truyencuoi.truyencuoi.extensions.request
import truyencuoioffline.truyencuoi.truyencuoi.network.http.ApiService

class SplashActivity : AppCompatActivity() {
    lateinit var apiService: ApiService
    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        runOnUiThread {
            if (isOnline(this)) {
                cache()
            }
        }
        val handler = Handler()
        handler.postDelayed({
            run {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }, 2000)

    }

    fun cache() {
        apiService = getHttpService().create(ApiService::class.java)
        val saveResponstToRealm = fun(it: List<RealmObject>) {
            realm.beginTransaction()
            realm.copyToRealmOrUpdate(it)
            realm.commitTransaction()
        }
        apiService.listCategories().request {
            saveResponstToRealm(it.body())
        }
        apiService.listPosts("", 0).request {
            saveResponstToRealm(it.body())
            for (story in it.body()) {
                Log.d("cache_image",ApiConstant.STATIC_POST_URL + story.id)
                imvCache.loadImage(ApiConstant.STATIC_POST_URL + story.id)
            }
        }
    }
}
