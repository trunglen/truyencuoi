package truyencuoioffline.truyencuoi.truyencuoi.ui.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import io.realm.Realm
import truyencuoioffline.truyencuoi.truyencuoi.extensions.getHttpService
import truyencuoioffline.truyencuoi.truyencuoi.network.http.ApiService
import truyencuoioffline.truyencuoi.truyencuoi.R

open class BaseActivity() : AppCompatActivity() {
    lateinit var apiService: ApiService
    var realm: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        apiService = getHttpService().create(ApiService::class.java)
        delegate.supportActionBar?.setIcon(R.drawable.ic_logo)
    }

    fun requestAds(ads:AdView) {
        MobileAds.initialize(this,"ca-app-pub-9374507533753194~3747257296")
        val adRequest = AdRequest.Builder().build()
        ads.loadAd(adRequest)
    }
}