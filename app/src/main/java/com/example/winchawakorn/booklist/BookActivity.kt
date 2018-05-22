package com.example.winchawakorn.booklist

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import booklist.models.Book
import booklist.models.BookRepository
import booklist.presenters.BookPresenter
import booklist.presenters.BookView
import com.example.winchawakorn.booklist.models.RealBookRepository
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity(), BookView {

    var adapter: ArrayAdapter<Book>? = null

    lateinit var presenter: BookPresenter
    lateinit var repository: BookRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)
        repository = RealBookRepository()
        presenter = BookPresenter(this, repository)
        presenter.start()
    }

    override fun setBookList(books: ArrayList<Book>) {
        adapter = ArrayAdapter<Book>(this, android.R.layout.simple_list_item_1, books)
        list_item.adapter = adapter
    }

    fun searchAction(view: View) {
        val searchTitle = titlesearch.text
        var searchTitleString: String = ""
        val searchYear = yearsearch.text
        var searchYearString: String = ""

        val titleCheck = searchTitle != null
        val yearCheck = searchYear != null

        if (titleCheck) searchTitleString = searchTitle.toString()
        if (yearCheck) searchYearString = searchYear.toString()
        val searchList: ArrayList<Book>
        if (titleCheck || yearCheck) {
            presenter.search(searchTitleString, searchYearString)
        }
    }
}