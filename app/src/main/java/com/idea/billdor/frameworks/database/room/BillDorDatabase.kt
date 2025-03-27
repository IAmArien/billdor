package com.idea.billdor.frameworks.database.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.idea.billdor.frameworks.dagger.modules.DatabaseModule
import com.idea.billdor.frameworks.database.dao.RecipesDao
import com.idea.billdor.frameworks.database.entities.MealRecipesEntity
import com.idea.billdor.frameworks.database.entities.RecipesEntity
import com.idea.billdor.frameworks.database.room.converters.RecipesConverter

@Database(entities = [RecipesEntity::class, MealRecipesEntity::class], version = 3)
@TypeConverters(RecipesConverter:: class)
abstract class BillDorDatabase : RoomDatabase() {

    abstract val recipesDao: RecipesDao

    companion object {
        @Volatile
        private var INSTANCE: BillDorDatabase? = null
        fun getInstance(context: Context): BillDorDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    val builder = Room.databaseBuilder(
                        context.applicationContext,
                        BillDorDatabase::class.java,
                        DatabaseModule.DATABASE_NAME
                    )
                    builder.fallbackToDestructiveMigration()
                    instance = builder.build()
                }
                INSTANCE = instance
                return instance
            }
        }
    }
}
