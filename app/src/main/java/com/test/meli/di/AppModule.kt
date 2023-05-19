package com.test.meli.di

import android.content.Context
import androidx.room.Room
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.meli.commons.Constants
import com.test.meli.commons.Constants.Companion.BASE_URL
import com.test.meli.data.local.database.MarketDb
import com.test.meli.data.remote.ProductDataRemoteImpl
import com.test.meli.data.remote.RetrofitServicesInterface
import com.test.meli.data.remote.sources.ProductDataRemoteSource
import com.test.meli.repository.ProductRepositoryImpl
import com.test.meli.repository.contracts.ProductRepositorySource
import com.test.meli.repository.mappers.ProductMapper
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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

    @Singleton
    @Provides
    fun getRetrofitInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Singleton
    @Provides
    fun getRetrofitServiceInterface(retrofit: Retrofit): RetrofitServicesInterface =
        retrofit.create(RetrofitServicesInterface::class.java)

    @Provides
    fun providesIoDispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun providesProductMapper(): ProductMapper = ProductMapper

}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class DataRemoteModule {

    @Binds
    abstract fun provideProductDataRemoteImpl(productDataRemoteImpl: ProductDataRemoteImpl): ProductDataRemoteSource
}

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideProductRepositoryImpl(repository: ProductRepositoryImpl): ProductRepositorySource
}

@Module
@InstallIn(ActivityComponent::class)
object presentationModule {

    @Provides
    fun materialAlertDialogBuilder(@ActivityContext context: Context): MaterialAlertDialogBuilder =
        MaterialAlertDialogBuilder(context)
}

