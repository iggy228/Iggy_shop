package sk.iggy.myecommerce

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_main.view.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import sk.iggy.myecommerce.database.AppDatabase
import sk.iggy.myecommerce.database.ProductFromDatabase
import sk.iggy.myecommerce.model.Product
import java.net.URL

class FragmentMain : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)

        doAsync {
            val json = URL("https://finepointmobile.com/data/products.json").readText()
            uiThread {
                val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            }
        }

        doAsync {
            val db = Room.databaseBuilder(activity!!.applicationContext, AppDatabase::class.java,
                "products_db").build()

            val products = db.productDao().getAll().map {
                Product(
                    it.title,
                    "https://finepointmobile.com/data/jeans1.jpg",
                    it.price,
                    "dadadasdadadadad",
                    false
                )
            }

            uiThread {
                root.recycler_view.apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)
                }
            }
        }

        root.progressBar.visibility = View.GONE

        val categories = listOf("shirts", "trousers", "underwears", "Iggy", "Noro", "Roxor", "Luko")

        root.categories_recycler.apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }

        return root
    }
}