package com.idea.billdor.frameworks.dagger.modules

import com.idea.billdor.frameworks.adapter.CoroutineCallAdapterFactory
import com.idea.billdor.frameworks.dagger.qualifiers.RecipesRetrofit
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitModule {
    
    const val BASE_URL = "https://dummyjson.com"

    @Provides
    @Singleton
    fun providesConverterFactory(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory())
        return MoshiConverterFactory.create(moshi.build())
    }

    @Provides
    @Singleton
    @RecipesRetrofit
    fun providesRecipesRetrofit(factory: MoshiConverterFactory): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(factory)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}
