package com.example.newyorktimesbestsellerbookapp.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.Observer
import com.example.newyorktimesbestsellerbookapp.R
import com.example.newyorktimesbestsellerbookapp.data.repository.BooksDataSource

class BooksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_books)

        val toolbarMain = findViewById<View>(R.id.toolbarMain) as Toolbar
        toolbarMain.title = getString(R.string.books_title)

        val viewModel: BooksViewModel = BooksViewModel.ViewModelFactory(BooksDataSource())
            .create(BooksViewModel::class.java)

        viewModel.booksLiveData.observe(this, Observer {
            it?.let { books ->
                with(recyclerBooks) {
                    layoutManager =
                        LinearLayoutManager(this@BooksActivity, RecyclerView.VERTICAL, false)
                    setHasFixedSize(true)
                    adapter = BooksAdapter(books) { book ->
                        val intent = BookDetailsActivity.getStartIntent(
                            this@BooksActivity,
                            book.title,
                            book.description
                        )
                        this@BooksActivity.startActivity(intent)
                    }
                }
            }
        })

        viewModel.viewFlipperLiveData.observe(this, Observer { it?.let { viewFlipper ->
            viewFlipperBooks.displayedChild = viewFlipper.first

            viewFlipper.second?.let { errorMessageResId ->
                textViewError.text = getString(errorMessageResId)
            }
        }
        })

        viewModel.getBooks()
    }


}