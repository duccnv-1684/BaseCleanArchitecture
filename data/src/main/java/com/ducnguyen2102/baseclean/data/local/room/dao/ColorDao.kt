package com.ducnguyen2102.baseclean.data.local.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ducnguyen2102.baseclean.data.local.room.entities.ColorEntity

@Dao
interface ColorDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(colors: List<ColorEntity>)

    @Query("SELECT * FROM colors")
    fun getColors(): List<ColorEntity>
}