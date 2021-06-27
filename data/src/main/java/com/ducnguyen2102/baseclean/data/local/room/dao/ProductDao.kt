package com.ducnguyen2102.baseclean.data.local.room.dao

import androidx.room.*
import com.ducnguyen2102.baseclean.data.local.room.entities.ProductEntity
import com.ducnguyen2102.baseclean.data.local.room.entities.ProductWithColorEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(productEntities: List<ProductEntity>)

    @Update
    fun update(vararg productEntities: ProductEntity): Int

    @Transaction
    @Query("SELECT * FROM products")
    fun getProducts(): List<ProductWithColorEntity>

    @Transaction
    @Query("SELECT * FROM products ORDER BY id LIMIT :size")
    fun getProducts(size: Int): Flow<List<ProductWithColorEntity>>

    @Transaction
    @Query("SELECT * FROM products WHERE id = :id")
    fun getProduct(id: Int): ProductWithColorEntity
}