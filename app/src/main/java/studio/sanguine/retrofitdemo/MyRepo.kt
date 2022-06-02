package studio.sanguine.retrofitdemo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyRepo (val inter : RetroApiInterface){

    suspend fun getAllBooks() = inter.getAllBooks()

    suspend fun createBooks(requestBody: RequestBody) = inter.createBooks(requestBody)

}