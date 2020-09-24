package sk.iggy.myecommerce

import android.content.DialogInterface
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.product_details.*

class ProductDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("name")
        product_title.text = title

        product_availability.setOnClickListener {
            AlertDialog.Builder(this).setMessage("This $title is out of stock!")
                .setPositiveButton("OK") {
                        p0, p1 ->
                }
                .create().show()
        }
    }
}