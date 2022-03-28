package com.fivestars.recyclerviewcheckboxaccessibilitysample

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.listoptions.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ColumnFragment.newInstance())
                .commitNow()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.accessibilty_options, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.row -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, RowFragment.newInstance())
                    .commitNow()
                true
            }
            R.id.column -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ColumnFragment.newInstance())
                    .commitNow()
                true
            }
            R.id.spannable -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, SpannableFragment.newInstance())
                    .commitNow()
                true
            }
            R.id.string_list -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, StringFragment.newInstance())
                    .commitNow()
                true
            }
            R.id.webview -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, WebViewFragment.newInstance())
                    .commitNow()
                true
            }
            R.id.new_activity -> {
                val newIntent = Intent(this, MainActivity::class.java)
                startActivity(newIntent)
                true
            }
            R.id.clickable_view -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.container, ClickableViewFragment.newInstance())
                    .commitNow()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}