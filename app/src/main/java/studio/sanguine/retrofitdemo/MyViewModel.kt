package studio.sanguine.retrofitdemo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MyViewModel : ViewModel(){

    val inter = RetroApiInterface.create()
    val repo = MyRepo(inter)

    var data = MutableLiveData<List<Book>>()

    var job : Job? = null

    fun getAllBooks(){
        job = CoroutineScope(Dispatchers.IO).launch{
            val res = repo.getAllBooks()
            if(res.isSuccessful)
                data.postValue(res.body())
        }
    }
}