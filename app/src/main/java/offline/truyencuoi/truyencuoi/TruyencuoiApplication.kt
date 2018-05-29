package offline.truyencuoi.truyencuoi

import android.app.Application
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import offline.truyencuoi.truyencuoi.common.ApiConstant
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class TruyencuoiApplication:Application() {
    companion object {
        val apiService = Retrofit.Builder()
        .baseUrl(ApiConstant.API_BASE_ENDPOINT)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    }
}