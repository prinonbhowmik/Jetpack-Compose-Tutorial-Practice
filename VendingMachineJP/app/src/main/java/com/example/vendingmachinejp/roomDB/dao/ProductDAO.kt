package com.example.vendingmachinejp.roomDB.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.vendingmachinejp.roomDB.model.ProductCartModel
import kotlinx.coroutines.flow.Flow


@Dao
interface ProductDAO {

    @Query("SELECT * FROM product_cart")
    fun getAllProducts(): Flow<List<ProductCartModel>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(
        product: ProductCartModel
    )

    @Query("UPDATE product_cart SET quantity = quantity + 1,actualPrice = presentPrice * (quantity+1) WHERE productId = :productID AND quantity<2")
    fun updateQuantity(productID: String)

    @Query("UPDATE product_cart SET quantity = quantity - 1,actualPrice = presentPrice * (quantity-1) WHERE productId = :productID")
    fun decreaseQuantity(productID: String)

    @Delete
    fun deleteUser(product: ProductCartModel)

    @Query("DELETE FROM product_cart")
    fun deleteAll()

}