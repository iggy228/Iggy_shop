package sk.iggy.myecommerce

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import sk.iggy.myecommerce.model.Product

class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener {
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("name", products[holder.adapterPosition].title)
            intent.putExtra("photoUrl", products[holder.adapterPosition].photoUrl)
            intent.putExtra("price", products[holder.adapterPosition].price.toString())
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        val product = products[position]
        holder.title.text = product.title
        holder.price.text = "${product.price}$"
        Picasso.get().load(product.photoUrl).into(holder.image)
    }

    override fun getItemCount() = products.size

}