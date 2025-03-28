package com.idea.billdor.frameworks.dagger.modules

import com.idea.billdor.frameworks.dagger.qualifiers.CoreRecipesRepository
import com.idea.billdor.frameworks.datasource.RecipesUseCases
import com.idea.core.recipes.repository.RecipesRepository
import com.idea.core.recipes.usecases.GetAllRecipeTagsAsync
import com.idea.core.recipes.usecases.GetAllRecipesAsync
import com.idea.core.recipes.usecases.GetLocalMealRecipes
import com.idea.core.recipes.usecases.GetLocalRecipes
import com.idea.core.recipes.usecases.GetMealRecipesAsync
import com.idea.core.recipes.usecases.GetRecipesByTagAsync
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCasesModule {

    @Provides
    fun providesRecipesUseCases(@CoreRecipesRepository repository: RecipesRepository): RecipesUseCases {
        return RecipesUseCases(
            getAllRecipesAsync = GetAllRecipesAsync(repository = repository),
            getMealRecipesAsync = GetMealRecipesAsync(repository = repository),
            getAllRecipeTagsAsync = GetAllRecipeTagsAsync(repository = repository),
            getRecipesByTagAsync = GetRecipesByTagAsync(repository = repository),
            getLocalRecipes = GetLocalRecipes(repository = repository),
            getLocalMealRecipes = GetLocalMealRecipes(repository = repository)
        )
    }
}
