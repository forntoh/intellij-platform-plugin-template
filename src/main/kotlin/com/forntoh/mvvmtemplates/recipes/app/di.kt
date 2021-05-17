package com.forntoh.mvvmtemplates.recipes.app

fun appModule(
    packageName: String,
    commonPackageName: String
) = """package $packageName

import android.content.Context
import $packageName.R
import $commonPackageName.event.EventBus
import $commonPackageName.pref.PreferenceRepository
import coil.ImageLoader
import coil.request.ImageRequest
import coil.util.CoilUtils
import coil.util.DebugLogger
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideImageLoader(
        @ApplicationContext context: Context
    ): ImageLoader = ImageLoader.Builder(context)
        .crossfade(true)
        .fallback(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .logger(DebugLogger())
        .okHttpClient {
            OkHttpClient.Builder()
                .cache(CoilUtils.createDefaultCache(context))
                .build()
        }
        .build()

    @Singleton
    @Provides
    fun provideLoadRequest(
        @ApplicationContext context: Context,
        preferenceRepository: PreferenceRepository
    ): ImageRequest.Builder = ImageRequest.Builder(context)
        .addHeader("Authorization", "Bearer +{preferenceRepository.userToken}")
        .crossfade(true)

    @Singleton
    @Provides
    fun provideEventBus(): EventBus = EventBus()
}""".replace('+', '$')