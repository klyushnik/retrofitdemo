package studio.sanguine.retrofitdemo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Observer
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations

@RunWith(JUnit4::class)
class MyViewModelTest {
    //@Mock

    //add dependencies for mockito
    //add class annotation @RunWith(JUnit4::class)
    //    given
    //identify all external dependencies and note it down
    //create any local objects needed
    //mock all identified dependencies (@Mock, @Before)
    //    when
    //call the respective function on the mocked object from the function that needs to be tested
    //    then (return dummy)
    //make the actual call - which is verify
    //assert

    lateinit var vm : MyViewModel
/*    lateinit var bookList : Observable<List<Book>>

    private fun setupObservers(){
        bookList = mock(Observer::class.java) as Observer<List<Book>>
    }*/

    @Mock
    lateinit var repository: MyRepo

   /* @Mock
    lateinit var inter : RetroApiInterface*/

    @Before
    fun setup(){
        MockitoAnnotations.openMocks(this)

        //repository = MyRepo(inter)
        //repository is mocked, thus no need to init
        //viewmodel is real, so need to init
        vm = MyViewModel()
    }

    @Test
    fun `given repository when calling booklist then list is empty and assert its empty`(){

    }

    @Test
    fun getApiBooksTest(){
        var fakeList = (listOf(
            Book(1, "title", "subtitle", "author", "publisher"),
            Book(2, "title2", "subtitle2", "author", "publisher")))
        Mockito.`when`(repository.getApiBooks())
            .thenReturn(Observable.fromArray(fakeList))
        var result = vm.getApiBooks()



        result.subscribeBy(
            onNext = {
                assertEquals(listOf(Book(1, "title", "subtitle", "author", "publisher")), it)
            },
            onError = {println(it)}
        )
    }

    @Test
    fun validBookTest(){
        val goodBook = Book(1, "title", "subtitle", "author", "publisher")
        assertEquals(true, Sample.validBook(goodBook))
    }
}