package com.example.gmaillayout

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gmaillayout.recylerview.EmailAdapter
import com.example.gmaillayout.recylerview.EmailData
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
import com.google.android.material.search.SearchBar
import com.google.android.material.search.SearchView
import java.time.LocalDateTime

class MainActivity : AppCompatActivity() {
    private fun setFABBottomMargin(fab: ExtendedFloatingActionButton, bottom: Int) {
        val density = resources.displayMetrics.density
        val params = fab.layoutParams as ViewGroup.MarginLayoutParams
        params.bottomMargin = (bottom * density).toInt()
        fab.layoutParams = params
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val searchBar = findViewById<SearchBar>(R.id.searchBar)
        searchBar.inflateMenu(R.menu.search_bar_menu)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.editText.addTextChangedListener(MenuWatcher(searchView))
        searchView.editText.setOnEditorActionListener { v, _, _ ->
            val text: String = v.text.toString()
            searchView.hide()
            searchBar.setText(text)
            true
        }

        val emailItems = listOf(
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), true),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), true),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), true),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
            EmailData("Google", "Welcome to Gmail", "Welcome to Gmail! You can now send emails, chat, and more.", LocalDateTime.now(), false),
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val emailAdapter = EmailAdapter(emailItems)
        recyclerView.adapter = emailAdapter

        val fab: ExtendedFloatingActionButton = findViewById(R.id.fab)
        recyclerView.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && fab.isExtended) {
                    fab.shrink()
                    setFABBottomMargin(fab, 15)
                }
                else if (dy < 0 && !fab.isExtended) {
                    fab.extend()
                    setFABBottomMargin(fab, 75)
                }
            }
        })
    }
}