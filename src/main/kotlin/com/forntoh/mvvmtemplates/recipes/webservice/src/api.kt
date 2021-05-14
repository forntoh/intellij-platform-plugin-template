package com.forntoh.mvvmtemplates.recipes.webservice.src

fun apiManager(
    packageName: String,
): String {

    val parentPackage = packageName.substring(0, packageName.lastIndexOf('.'))

    return """package $packageName

import $parentPackage.di.AuthenticatedOkHttpClient
import $parentPackage.di.BaseOkHttpClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiManager @Inject constructor(
    retrofitBuilder: Retrofit.Builder,
    @BaseOkHttpClient baseOkHttpClient: OkHttpClient,
    @AuthenticatedOkHttpClient userOkHttpClient: OkHttpClient,
) {

    private val baseRetrofit: Retrofit = retrofitBuilder
        .client(baseOkHttpClient)
        .build()

    private val authenticatedRetrofit: Retrofit = retrofitBuilder
        .client(userOkHttpClient)
        .build()

    val baseApi: BaseApiService = baseRetrofit.create(BaseApiService::class.java)

}
"""
}

fun baseApiService(
    packageName: String,
): String = """package $packageName

interface BaseApiService {

}
"""