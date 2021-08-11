package uz.revolution.viewpagertask2.retrofit

object Common {
    var BASE_URL = "https://api.unsplash.com/"

    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)


}