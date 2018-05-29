package offline.truyencuoi.truyencuoi.ui.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import offline.truyencuoi.truyencuoi.R
import offline.truyencuoi.truyencuoi.extensions.getHttpService
import offline.truyencuoi.truyencuoi.extensions.request
import offline.truyencuoi.truyencuoi.network.http.ApiService
import offline.truyencuoi.truyencuoi.network.models.Category
import offline.truyencuoi.truyencuoi.ui.adapter.CategoryAdapter

class MainActivity : AppCompatActivity() {

    lateinit var apiService: ApiService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        apiService = getHttpService().create(ApiService::class.java)
        apiService.listCategories().request {
            lvCategory.adapter = CategoryAdapter(it.body())
        }
    }

}
