package com.forntoh.mvvmtemplates.recipes.webservice.src

fun baseInterceptor(
    packageName: String
) = """package $packageName

import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Inject

class BaseInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val originalUrl = originalRequest.url

        val newUrl = originalUrl
            .newBuilder()
            .addQueryParameter("lang", Locale.getDefault().toLanguageTag())
            .build()

        val request = originalRequest
            .newBuilder()
            .addHeader("Accept", "application/json")
            .addHeader("Connection", "Keep-Alive")
            .addHeader("Content-Type", "application/json")
            .url(newUrl)
            .build()
        return chain.proceed(request)
    }

}
"""

fun tokenInterceptor(
    packageName: String,
    commonPackageName: String
) = """package $packageName

import $commonPackageName.pref.PreferenceRepository
import okhttp3.Interceptor
import javax.inject.Inject

class TokenInterceptor @Inject constructor(
    private val preferenceRepository: PreferenceRepository,
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain) = chain.proceed(
        chain.request()
            .newBuilder()
            .addHeader("Authorization", "Bearer +{preferenceRepository.userToken}")
            .build()
    )
}
""".replace('+', '$')

