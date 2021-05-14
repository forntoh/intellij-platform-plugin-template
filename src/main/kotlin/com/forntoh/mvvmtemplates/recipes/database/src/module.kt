package com.forntoh.mvvmtemplates.recipes.database.src

fun module(
    packageName: String,
    commonPackageName: String,
) = """package $packageName

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import $commonPackageName.pref.PreferenceRepository

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase = AppDatabase(context)

    @Provides
    @Singleton
    fun provideAppStorage(
        @ApplicationContext context: Context
    ): PreferenceRepository = PreferenceRepository(context)

}
"""
