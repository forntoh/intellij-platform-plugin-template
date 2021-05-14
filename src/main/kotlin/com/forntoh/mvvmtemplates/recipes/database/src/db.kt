package com.forntoh.mvvmtemplates.recipes.database.src

fun database(
    packageName: String,
    databaseName: String,
) = """package $packageName

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import javax.inject.Singleton

import $packageName.converters.Converters

@Singleton
@Database(
    entities = [
        //TODO: Insert Entities here
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    //TODO: Insert DAOs here

    companion object {
        @Volatile
        private var instance: AppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "$databaseName.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}
"""
