package sk.iggy.myecommerce

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.room.Room
import kotlinx.android.synthetic.main.fragment_admin.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.support.v4.toast
import org.jetbrains.anko.uiThread
import sk.iggy.myecommerce.database.AppDatabase
import sk.iggy.myecommerce.database.ProductFromDatabase

class FragmentAdmin : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_button.setOnClickListener {
            val title = product_title.text.toString()
            val price = product_price.text.toString().toDouble()

            doAsync {

                val db = Room.databaseBuilder(activity!!.applicationContext,
                    AppDatabase::class.java, "products_db").build()

                db.productDao().insertAll(ProductFromDatabase(null, title, price))

                uiThread {

                }
                Toast.makeText(activity, "Product was add!", Toast.LENGTH_SHORT).show()
                product_title.text.clear()
                product_price.text.clear()
            }
            activity?.supportFragmentManager?.beginTransaction()?.replace(R.id.frame_layout, FragmentMain())?.commit()
        }
    }
}