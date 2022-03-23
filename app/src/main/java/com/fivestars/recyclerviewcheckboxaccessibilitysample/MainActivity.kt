package com.fivestars.recyclerviewcheckboxaccessibilitysample

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.ColumnFragment
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.RowFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ColumnFragment.newInstance())
                .commitNow()
        }
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
            else -> super.onOptionsItemSelected(item)
        }
    }
}