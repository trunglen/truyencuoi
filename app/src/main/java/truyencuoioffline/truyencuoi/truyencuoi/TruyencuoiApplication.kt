package truyencuoioffline.truyencuoi.truyencuoi

import android.app.Application
import com.facebook.stetho.Stetho
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.realm.Realm
import io.realm.RealmConfiguration
import truyencuoioffline.truyencuoi.truyencuoi.common.ApiConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.uphyca.stetho_realm.RealmInspectorModulesProvider

class TruyencuoiApplication:Application() {
    companion object {
        val apiService = Retrofit.Builder()
        .baseUrl(ApiConstant.API_BASE_ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }

    override fun onCreate() {
        super.onCreate()
        initRealm()
    }

    fun initRealm() {
        var c = Realm.init(this)
        val config = RealmConfiguration.Builder().deleteRealmIfMigrationNeeded()
        Realm.setDefaultConfiguration(config.build())
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(RealmInspectorModulesProvider.builder(this).build())
                        .build());
    }
}