package com.example.gmaillayout

import android.text.Editable
import android.text.TextWatcher
import com.google.android.material.search.SearchView

class MenuWatcher(private val searchView: SearchView): TextWatcher {
    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

    override fun afterTextChanged(s: Editable?) {
        if (!s.isNullOrEmpty()) searchView.toolbar.menu.clear()
        else if (!searchView.toolbar.menu.hasVisibleItems()) searchView.inflateMenu(R.menu.search_view_menu)
    }
}