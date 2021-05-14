package com.forntoh.mvvmtemplates.recipes.webservice.src

fun baseUrl(
    packageName: String,
    useHttps: Boolean,
    domain: String,
    port: String,
) = """package $packageName

object BaseUrl {

    private const val PROTOCOL = ${if (useHttps) "\"https://\"" else "\"http://\""}
    private const val DOMAIN = "$domain"
    private const val PORT = "$port"

    @JvmStatic
    val defaultBaseUrl: String = "+PROTOCOL+DOMAIN+PORT"
}
""".replace('+', '$')

fun endPoints(
    packageName: String
) = """package $packageName

object EndPoints {

    private const val BASE = "/api"

    const val API_EXAMPLE_PATH = "+{BASE}/v1/example"
}
""".replace('+', '$')