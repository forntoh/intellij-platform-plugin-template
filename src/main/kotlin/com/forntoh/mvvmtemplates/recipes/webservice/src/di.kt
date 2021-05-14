package com.forntoh.mvvmtemplates.recipes.webservice.src

fun networkModule(
    packageName: String
): String {

    val parentPackage = packageName.substring(0, packageName.lastIndexOf('.'))

    return """package $packageName
    
import $parentPackage.base.BaseUrl
import $parentPackage.interceptors.BaseInterceptor
import $parentPackage.interceptors.TokenInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.net.ssl.SSLSocketFactory
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @BaseOkHttpClient
    @Provides
    fun provideBaseOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
    ): OkHttpClient = okHttpClientBuilder
        .build()

    @AuthenticatedOkHttpClient
    @Provides
    fun provideAuthenticatedOkHttpClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient = okHttpClientBuilder
        .addInterceptor(tokenInterceptor)
        .build()

    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        baseInterceptor: BaseInterceptor,
        sslSocketFactory: SSLSocketFactory,
        trustManager: TrustManager
    ): OkHttpClient.Builder = OkHttpClient.Builder()
        .sslSocketFactory(sslSocketFactory, trustManager as X509TrustManager)
        .hostnameVerifier { _, _ -> true }
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(baseInterceptor)
        .retryOnConnectionFailure(true)

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BaseUrl.defaultBaseUrl)
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class AuthenticatedOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseOkHttpClient

"""
}

fun sslModule(
    packageName: String
) = """package $packageName

import android.annotation.SuppressLint
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.security.KeyStore
import java.security.SecureRandom
import java.security.cert.X509Certificate
import java.util.*
import javax.net.ssl.*

@SuppressLint("TrustAllX509TrustManager")
@Module
@InstallIn(SingletonComponent::class)
object SslModule {

    @Provides
    fun provideSslSocketFactory(): SSLSocketFactory = with(SSLContext.getInstance("SSL")) {
        init(null, arrayOf<TrustManager>(
            object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) = Unit

                override fun checkServerTrusted(
                    chain: Array<X509Certificate>,
                    authType: String
                ) = Unit

                override fun getAcceptedIssuers(): Array<X509Certificate?> = arrayOfNulls(0)
            }
        ), SecureRandom())
        socketFactory
    }

    @Provides
    fun provideTrustManagers(): TrustManager =
        with(TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm())) {
            init(null as KeyStore?)
            check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
                "Unexpected default trust managers:" + Arrays.toString(trustManagers)
            }
            trustManagers[0]
        }
}
"""

