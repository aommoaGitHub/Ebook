package booklist.presenters

import booklist.models.BookRepository
import java.util.*

class BookPresenter(val view: BookView,
                    val repository: BookRepository) : Observer {

    fun start() {
        repository.addObserver(this)
        repository.loadAllBooks()
        view.setBookList(repository.getBooks())
    }

    override fun update(obj: Observable?, arg: Any?) {
        if (obj == repository) {
            view.setBookList(repository.getBooks())
        }
    }

    fun search(title: String, year: String) {
        val searchList = repository.search(title, year)
        view.setBookList(searchList)
    }

}
