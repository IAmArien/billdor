package com.idea.billdor.frameworks.dagger.modules

import com.idea.billdor.frameworks.dagger.qualifiers.CoreRecipesRepository
import com.idea.billdor.frameworks.datasource.RecipesDataSourceImpl
import com.idea.core.recipes.repository.RecipesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @CoreRecipesRepository
    fun providesRecipesDataSource(dataSourceImpl: RecipesDataSourceImpl): RecipesRepository {
        return RecipesRepository(dataSource = dataSourceImpl)
    }
}
