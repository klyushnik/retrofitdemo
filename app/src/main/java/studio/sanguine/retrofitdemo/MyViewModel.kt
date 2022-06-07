package studio.sanguine.retrofitdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import okhttp3.RequestBody

class MyViewModel : ViewModel(){

    val inter = RetroApiInterface.create()
    val repo = MyRepo(inter)

    var data = MutableLiveData<List<Book>>()

    var job : Job? = null

    fun getAllBooks(){
        job = CoroutineScope(Dispatchers.IO).launch{
            val res = repo.getAllBooks()
            if(res.isSuccessful){}
                data.postValue(res.body())
        }
    }

    fun createBooks(requestBody: RequestBody) {
        CoroutineScope(Dispatchers.IO).launch {
            var res = repo.createBooks(requestBody)
            if(res.isSuccessful) {
                // res now is json
                val gson = GsonBuilder().setPrettyPrinting().create()
                val pJson = gson.toJson(
                    JsonParser.parseString(
                        res.body()?.toString()
                    )
                )
                println(pJson)
            }

        }
    }

}