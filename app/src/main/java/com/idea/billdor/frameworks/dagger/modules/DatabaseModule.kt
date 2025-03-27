package com.idea.billdor.frameworks.dagger.modules

import android.content.Context
import com.idea.billdor.frameworks.database.room.BillDorDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    const val DATABASE_NAME = "tokyo_athena.db"

    @Provides
    fun providesBillDorDatabase(@ApplicationContext context: Context): BillDorDatabase {
        return BillDorDatabase.getInstance(context = context)
    }
}
