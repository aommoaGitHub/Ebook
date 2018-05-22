package booklist.models

class MockBookRepository : BookRepository() {
    val bookList = ArrayList<Book>()
    override fun loadAllBooks() {
        bookList.clear()
        bookList.add(Book(1, "How to win BNK48 election", 500.0))
        bookList.add(Book(2, "How to write Android App", 199.0))
        bookList.add(Book(5, "Sleep today", 39.0))
        bookList.add(Book(7, "Hey", 20.0))
        setChanged()
        notifyObservers()
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    override fun search(title: String, year: String): ArrayList<Book> {
        return ArrayList()
    }
}