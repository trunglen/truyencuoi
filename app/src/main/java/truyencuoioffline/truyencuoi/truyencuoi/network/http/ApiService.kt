package truyencuoioffline.truyencuoi.truyencuoi.network.http

import io.reactivex.Observable
import truyencuoioffline.truyencuoi.truyencuoi.network.models.Category
import truyencuoioffline.truyencuoi.truyencuoi.network.models.Story
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("public/post/list")
    fun listPosts(@Query("cat_id") catID: String, @Query("page") page: Int): Observable<Response<List<Story>>>

    @GET("public/category/list")
    fun listCategories(): Observable<Response<List<Category>>>
}