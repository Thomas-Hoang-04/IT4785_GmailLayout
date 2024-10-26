package com.example.gmaillayout

import android.os.Bundle
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
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

        val searchBar = findViewById<SearchBar>(R.id.searchBar)
        searchBar.inflateMenu(R.menu.search_bar_menu)

        val searchView = findViewById<SearchView>(R.id.searchView)
        searchView.removeAllHeaderViews()
        searchView.editText.addTextChangedListener(MenuWatcher(searchView))
        searchView.editText.setOnEditorActionListener { v, _, _ ->
            val text: String = v.text.toString()
            searchView.hide()
            searchBar.setText(text)
            true
        }

        val emailItems = listOf(
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.now(), isFavor = false, false),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.now(), isFavor = true, false),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.now(), isFavor = false, true),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.now(), isFavor = true, true),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.now(), isFavor = false, false),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2024, 10, 5, 15, 10), isFavor = true, false),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2024, 9, 10, 12, 35), isFavor = false, true),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2024, 8, 25, 8, 50), isFavor = true, true),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2024, 7, 5, 17, 0), isFavor = false, false),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2024, 6, 10, 13, 15), isFavor = true, false),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2024, 5, 15, 7, 45), isFavor = false, true),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2024, 4, 20, 16, 20), isFavor = true, true),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2024, 3, 25, 11, 0), isFavor = false, false),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2024, 2, 5, 14, 30), isFavor = true, false),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2024, 1, 10, 9, 15), isFavor = false, true),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2023, 12, 1, 15, 0), isFavor = true, true),
            EmailData("John Doe", "Meeting", "Hey, let's meet at 2 PM", LocalDateTime.of(2023, 11, 15, 10, 45), isFavor = false, false),
            EmailData("Jane Doe", "Dinner", "Hey, let's have dinner tonight", LocalDateTime.of(2023, 10, 20, 8, 30), isFavor = true, false)
        )

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val emailAdapter = EmailAdapter(emailItems)
        recyclerView.adapter = emailAdapter

        val mainView: NestedScrollView = findViewById(R.id.main_content)
        val fab: ExtendedFloatingActionButton = findViewById(R.id.fab)
        mainView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY) {
                fab.shrink()
                setFABBottomMargin(fab, 20)
            } else {
                fab.extend()
                setFABBottomMargin(fab, 70)
            }
        }
    }
}