package studio.sanguine.retrofitdemo

import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.MockitoAnnotations
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@RunWith(JUnit4::class)
class RetroApiInterfaceTest {
    lateinit var inter : RetroApiInterface
    lateinit var mockWebServer : MockWebServer

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        mockWebServer = MockWebServer()
        inter = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(mockWebServer.url(""))

            .build().create(RetroApiInterface::class.java)
    }

    @Test
    fun getAllBooksTest() {

        var mockRes = MockResponse()
        mockRes.setBody("[]")
        mockWebServer.enqueue(mockRes)

        runBlocking {
            val res = inter.getAllBooks()
            val req = mockWebServer.takeRequest()

            assertEquals("/books.json", req.path)
            assertEquals(true, res.body()?.isEmpty() == true)


        }

    }

    @After
    fun destroy(){
        mockWebServer.shutdown()
    }

}