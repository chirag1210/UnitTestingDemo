package com.chirag.unittestingapp.di

import com.chirag.unittestingapp.data.remote.PixabayAPI
import com.chirag.unittestingapp.repositories.DefaultShoppingRepository
import com.chirag.unittestingapp.repositories.ShoppingRepository
import com.chirag.unittestingapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun providePixabayApi(): PixabayAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(PixabayAPI::class.java)
    }

    @Singleton
    @Provides
    fun provideDefaultShoppingRepository(
        api: PixabayAPI
    ) = DefaultShoppingRepository(api) as ShoppingRepository
}