package offline.truyencuoi.truyencuoi.extensions

import android.support.v7.app.AppCompatActivity
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import offline.truyencuoi.truyencuoi.TruyencuoiApplication
import retrofit2.Retrofit

/**
 * Remember to set the android:parentActivityName attribute on the activity you are calling this
 * from!
 */
fun AppCompatActivity.enableToolbarBackButton() {
    delegate.supportActionBar?.setDisplayHomeAsUpEnabled(true)
}

fun AppCompatActivity.getHttpService(): Retrofit {
    return TruyencuoiApplication.apiService
}

fun <T> Observable<T>.request(success: (T) -> Unit) {
    subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread()).subscribe {
                success(it)
            }
}
