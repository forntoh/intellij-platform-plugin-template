package com.forntoh.mvvmtemplates.recipes.webservice.src

fun baseNetworkDataSource(
    packageName: String
): String {

    val parentPackage = packageName.substring(0, packageName.lastIndexOf('.'))

    return """package $packageName
    
import $parentPackage.dto.ErrorDTO
import $parentPackage.dto.Resource
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Response

abstract class BaseNetworkDataSource {

    protected suspend fun <T : Resource<T>> apiCover(api: suspend () -> Response<T>) =
        baseApiCover(api)

    protected suspend fun <T> apiCoverB(api: suspend () -> Response<T>): T? {
        return try {
            val fetchedData = api.invoke()
            if (fetchedData.isSuccessful) fetchedData.body()
            else null
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private suspend fun <T : Resource<T>> baseApiCover(
        api: suspend () -> Response<T>
    ): Resource<out Any> {
        return try {
            val fetchedData = api.invoke()
            if (fetchedData.isSuccessful) fetchedData.body() as Resource<T>
            else Gson().fromJson(
                fetchedData.errorBody()?.charStream(),
                object : TypeToken<Resource<ErrorDTO>>() {}.type
            )
        } catch (e: Exception) {
            e.printStackTrace()
            val data = ErrorDTO(e.message!!, e.message!!, e.message!!)
            Resource(success = false, data = data)
        }
    }

}
"""
}
