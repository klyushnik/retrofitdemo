package studio.sanguine.retrofitdemo

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
class MyRepoTest {

    @Mock
    lateinit var inter: RetroApiInterface

    lateinit var repo: MyRepo

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)

        repo = MyRepo(inter)
    }

    @Test
    fun getAllBooksTest(){
        var fakeList = (listOf(
            Book(1, "title", "subtitle", "author", "publisher")))

        //if async/suspend function, run inside runBlocking
        runBlocking {
            Mockito.`when`(inter.getAllBooks())
                .thenReturn(Response.success(fakeList))

            var response = repo.getAllBooks()


            assertEquals(fakeList, response.body())
        }
    }
}