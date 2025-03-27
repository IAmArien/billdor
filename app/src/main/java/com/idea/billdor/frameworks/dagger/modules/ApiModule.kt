package com.idea.billdor.frameworks.dagger.modules

import com.idea.billdor.frameworks.api.RecipesApi
import com.idea.billdor.frameworks.dagger.qualifiers.RecipesRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    fun providesRecipesApi(@RecipesRetrofit retrofit: Retrofit): RecipesApi {
        return retrofit.create(RecipesApi::class.java)
    }
}
