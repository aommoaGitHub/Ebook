package booklist.presenters

import booklist.models.Book

interface BookView {
    fun setBookList(books: ArrayList<Book>)
}