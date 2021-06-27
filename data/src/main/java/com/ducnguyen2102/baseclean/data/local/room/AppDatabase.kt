package com.ducnguyen2102.baseclean.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ducnguyen2102.baseclean.data.Config
import com.ducnguyen2102.baseclean.data.local.room.dao.ColorDao
import com.ducnguyen2102.baseclean.data.local.room.dao.ProductDao
import com.ducnguyen2102.baseclean.data.local.room.entities.ColorEntity
import com.ducnguyen2102.baseclean.data.local.room.entities.ProductEntity

@Database(entities = [ProductEntity::class, ColorEntity::class], version = Config.DATABASE_VERSION)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao

    abstract fun colorDao(): ColorDao
}