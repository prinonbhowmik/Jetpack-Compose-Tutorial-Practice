package com.example.vendingmachinejp.roomDB.dbInstanse

import android.content.Context
import androidx.room.Room
import com.example.vendingmachinejp.roomDB.dao.ProductDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class ProductDBModule {
    @Provides
    fun provideChannelDao(productDB: ProductDB): ProductDAO {
        return productDB.productDao()
    }

    @Provides
    @Singleton
    fun provideProductDB(@ApplicationContext appContext: Context): ProductDB {
        return Room.databaseBuilder(
            appContext,
            ProductDB::class.java,
            "ProductDB"
        ).build()
    }
}