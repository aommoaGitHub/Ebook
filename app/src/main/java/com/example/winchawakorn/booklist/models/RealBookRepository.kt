package com.example.winchawakorn.booklist.models

import android.os.AsyncTask
import booklist.models.Book
import booklist.models.BookRepository
import org.json.JSONArray
import java.net.URL

class RealBookRepository : BookRepository() {
    val bookList = ArrayList<Book>()

    override fun loadAllBooks() {
        bookList.clear()
        updateBooks()
        setChanged()
        notifyObservers()
    }

    override fun getBooks(): ArrayList<Book> {
        return bookList
    }

    private fun updateBooks() {
        val task = BookLoaderTask()
        task.execute()
    }

    override fun search(title: String, year: String): ArrayList<Book> {
        val titleBoolean = (title != "")
        val yearBoolean = (year != "")
        val searchList: ArrayList<Book> = ArrayList()
        for (b: Book in bookList) {
            val titleMatch = b.title.toLowerCase().contains(title.toLowerCase())
            val yearMatch = b.publicationYear.toString().contains(year)
            // case 1 search by both title and year
            if (titleBoolean && yearBoolean) {
                if (titleMatch && yearMatch) {
                    // sort
                    searchList.add(b)
                }
            }
            // case 2 search by title
            else if (titleBoolean && titleMatch) {
                // sort
                searchList.add(b)
            }
            // case 3 search by year
            else if (yearBoolean && yearMatch) {
                searchList.add(b)
            }
        }
        if (!titleBoolean && !yearBoolean) {
            searchList.clear()
            searchList.addAll(bookList)
        }
        // sort
        searchList.sortWith(compareBy({ it.publicationYear }, { it.title }))
        return searchList
    }

    inner class BookLoaderTask : AsyncTask<String, String, String>() {
        override fun doInBackground(vararg p0: String?): String {
            println("1")
            val url = URL("https://theory.cpe.ku.ac.th/~jittat/courses/sw-spec/ebooks/books.json")
            println("2")
            val json = url.readText().toString()
            println("3")
            return json
        }

        override fun onPostExecute(result: String?) {
            if (result != null) {
                val json: JSONArray = JSONArray(result)
                var list: String = ""
                for (i in 0..(json.length() - 1)) {
                    bookList.add(Book(json.getJSONObject(i).getInt("id"), json.getJSONObject(i).getString("title"), json.getJSONObject(i).getDouble("price"), json.getJSONObject(i).getInt("pub_year"), json.getJSONObject(i).getString("img_url")))
                }
            }
        }
    }
}