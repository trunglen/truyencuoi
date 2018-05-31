package offline.truyencuoi.truyencuoi.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.realm.Realm
import offline.truyencuoi.truyencuoi.extensions.getHttpService
import offline.truyencuoi.truyencuoi.network.http.ApiService
import offline.truyencuoi.truyencuoi.R

open class BaseActivity() : AppCompatActivity() {
    lateinit var apiService: ApiService
    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = getHttpService().create(ApiService::class.java)
        Log.d("actionbar",delegate.supportActionBar?.toString())
        delegate.supportActionBar?.setIcon(R.drawable.ic_logo)
    }
}