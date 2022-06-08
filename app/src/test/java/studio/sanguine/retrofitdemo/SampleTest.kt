package studio.sanguine.retrofitdemo

import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SampleTest {
    @Test
    fun validBookTest(){
        val goodBook = Book(1, "title", "subtitle", "author", "publisher")
        assertEquals(true, Sample.validBook(goodBook))
    }

    @Test
    fun invalidBookTest(){
        val badBook = Book(null, "title", "subtitle", "", "publisher")
        assertEquals(false, Sample.validBook(badBook))
    }
}