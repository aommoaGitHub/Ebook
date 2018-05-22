package booklist.models

import java.util.*
import kotlin.collections.ArrayList

abstract class BookRepository : Observable() {
    abstract fun loadAllBooks()
    abstract fun getBooks(): ArrayList<Book>
    abstract fun search(title: String, year: String): ArrayList<Book>
}