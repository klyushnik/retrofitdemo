package studio.sanguine.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import okhttp3.MediaType
import okhttp3.RequestBody
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var titleText: EditText
    lateinit var subtitleText:EditText
    lateinit var authorText: EditText
    lateinit var publisherText:EditText
    lateinit var addButton: Button
    lateinit var vm: MyViewModel
    lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        titleText = findViewById(R.id.titleText)
        subtitleText = findViewById(R.id.subtitleText)
        authorText = findViewById(R.id.authorText)
        publisherText = findViewById(R.id.publisherText)
        addButton = findViewById(R.id.addButton)


        vm = ViewModelProvider(this).get(MyViewModel::class.java)

        val data = ArrayList<Book>()


        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        adapter = MyAdapter(data)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*vm.data.observe(this){
            data.clear()
            data.addAll(it)
            adapter.notifyDataSetChanged()
        }

        vm.getAllBooks()*/
/*
        vm.getApiBooks()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy (
                onNext = {
                    data.clear()
                    data.addAll(it)
                    adapter.notifyDataSetChanged()
                },
                onError = {
                    println("onError: ")
                    println(it)
                }
            )
        */


        var observer = object: Observer<List<Book>> {
            override fun onSubscribe(p0: Disposable) {
                println("subscribed")
            }

            override fun onNext(p0: List<Book>) {
                data.clear()
                data.addAll(p0)
                adapter.notifyDataSetChanged()
            }

            override fun onError(p0: Throwable) {
                println("error")
            }

            override fun onComplete() {
                println("done")
            }
        }

        vm.getApiBooks().subscribe(observer)


    }


    fun addToList(view: View){
        /*//create a json object
        val book = Book(
            0,
            titleText.text.toString(),
            subtitleText.text.toString(),
            authorText.text.toString(),
            publisherText.text.toString())
            .toJson()
        *//*val jArray = JSONArray()
        jArray.put(book)
        for(i in adapter.data){
            jArray.put(i.toJson())
        }

        println(jArray.toString())*//*

        val string = book.toString()
        val type = MediaType.parse("application/json; charset=utf-8")
        val requestBody = RequestBody.create(type, string)

        vm.createBooks(requestBody)*/
    }

    fun refresh(view : View){
        vm.getAllBooks()
    }
}