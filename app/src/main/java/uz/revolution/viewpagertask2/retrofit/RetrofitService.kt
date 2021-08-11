package uz.revolution.viewpagertask2.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import uz.revolution.viewpagertask2.models.Photo

interface RetrofitService {

    @GET("photos")
    fun getPhotos(
        @Query("client_id") clientID: String,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ): Call<List<Photo>>

    @GET("photos")
    fun getNewPhotos(
        @Query("client_id") clientID: String,
        @Query("per_page") per_page: Int,
        @Query("order_by") order_by: String
    ):Call<List<Photo>>

    @GET("photos/{random}")
    fun getQueryPhotos(
        @Path("random") random:String,
        @Query("client_id") clientID: String,
        @Query("query") query: String,
        @Query("count") count: Int
    ):Call<List<Photo>>
}