package com.forntoh.mvvmtemplates.recipes.repository.src

fun appRepoModule(
    packageName: String,
): String = """package $packageName

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object AppRepoModule {

}
"""

fun repoModule(
    packageName: String,
): String = """package $packageName

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object RepoModule {

}
"""