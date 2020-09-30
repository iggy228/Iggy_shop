package sk.iggy.myecommerce

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import sk.iggy.myecommerce.database.AppDatabase
import sk.iggy.myecommerce.database.ProductFromDatabase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FragmentMain()).commit()

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            drawer_layout.closeDrawers()

            when (it.itemId) {
                R.id.drawer_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FragmentMain()).commit()
                }
                R.id.drawer_shirts -> Log.d("iggy", "pressed shirts")
                R.id.drawer_trousers -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FragmentTrousers()).commit()
                    Log.d("iggy", "pressed trousers")
                }
                R.id.drawer_underwear -> Log.d("iggy", "pressed underwear")
                R.id.drawer_settings -> Log.d("iggy", "pressed setting")
                R.id.drawer_admin -> supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FragmentAdmin()).commit()
            }
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }
}