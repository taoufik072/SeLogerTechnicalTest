package fr.benguiza.selogertest

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import fr.benguiza.selogertest.tools.extensions.unsafeLazy


abstract class BaseActivity(@LayoutRes private val layoutResourceId: Int) : AppCompatActivity() {

    private val toolbar by unsafeLazy {
        findViewById<Toolbar>(
            R.id.app_toolbar
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutResourceId)
        setSupportActionBar(toolbar)
        supportActionBar.apply { title = getString(R.string.app_nam) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
