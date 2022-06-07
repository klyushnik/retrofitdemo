package studio.sanguine.retrofitdemo

import io.reactivex.rxjava3.core.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetroApiInterface {

    @GET("books.json")
    fun getApiBooks() : Observable<List<Book>>

    @GET("books.json")
    suspend fun getAllBooks(): Response<List<Book>>

    @POST("/post")
    suspend fun createBooks(@Body requestBody: RequestBody) : Response<ResponseBody>

    //singleton
    companion object {
        val BASE_URL = "https://klyushnik.github.io/"
        fun create() : RetroApiInterface {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build()
            return retrofit.create(RetroApiInterface::class.java)
        }
    }
}