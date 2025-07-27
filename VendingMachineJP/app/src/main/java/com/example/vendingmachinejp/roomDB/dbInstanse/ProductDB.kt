package com.example.vendingmachinejp.roomDB.dbInstanse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vendingmachinejp.roomDB.dao.ProductDAO
import com.example.vendingmachinejp.roomDB.model.ProductCartModel


@Database(entities = [ProductCartModel::class], version = 1)
abstract class ProductDB : RoomDatabase() {

    abstract fun productDao(): ProductDAO
}