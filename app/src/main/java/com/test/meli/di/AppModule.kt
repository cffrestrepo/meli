package com.test.meli.di

import android.content.Context
import androidx.room.Room
import com.test.meli.constants.Constants
import com.test.meli.data.local.database.MarketDb
import com.test.meli.repository.ProductRepositoryImpl
import com.test.meli.repository.contracts.ProductRepositorySource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRoomInstance(@ApplicationContext context: Context) = Room.databaseBuilder(
        context,
        MarketDb::class.java,
        Constants.NAME_DATA_BASE
    )// Wipes and rebuilds instead of migrating if no Migration object.
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideProductsDao(dataBase: MarketDb) = dataBase.productDao()
}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProductRepositoryImpl(repository: ProductRepositoryImpl): ProductRepositorySource
}
