package studio.sanguine.retrofitdemo

object Sample {
    fun validBook(book: Book) : Boolean{
        if(book.id == null){
            return false
        }
        if(book.title.isEmpty()){
            return false
        }
        if(book.author.isEmpty()){
            return false
        }

        return true
    }
}