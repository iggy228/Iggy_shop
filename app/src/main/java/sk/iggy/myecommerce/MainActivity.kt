package sk.iggy.myecommerce

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_context.*
import sk.iggy.myecommerce.model.Product

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        nav_view.setNavigationItemSelectedListener {
            it.isChecked = true
            drawer_layout.closeDrawers()
            when (it.itemId) {
                R.id.drawer_shirts -> Log.d("iggy", "pressed shirts")
                R.id.drawer_trousers -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FragmentTrousers()).commit()
                    Log.d("iggy", "pressed trousers")
                }
                R.id.drawer_underwear -> Log.d("iggy", "pressed underwear")
                R.id.drawer_settings -> Log.d("iggy", "pressed setting")
            }
            true
        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }

        val products = ArrayList<Product>()

        for (i in 0 until 100) {
            products.add(Product("Shirt #${i+1}", "https://via.placeholder.com/200x200/0000cc", 0.99))
        }

        recycler_view.apply {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = ProductsAdapter(products)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawer_layout.openDrawer(GravityCompat.START)
        return true
    }
}