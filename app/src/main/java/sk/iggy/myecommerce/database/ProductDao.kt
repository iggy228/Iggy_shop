package sk.iggy.myecommerce.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import sk.iggy.myecommerce.model.Product

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductFromDatabase")
    fun getAll() : List<ProductFromDatabase>

    @Insert
    fun insertAll(vararg products: ProductFromDatabase)
}