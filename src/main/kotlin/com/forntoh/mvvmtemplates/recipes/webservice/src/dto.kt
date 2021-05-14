package com.forntoh.mvvmtemplates.recipes.webservice.src

fun resource(
    packageName: String
) = """package $packageName

class Resource<T>(
    val success: Boolean,
    val data: T,
)
"""

fun errorDto(
    packageName: String
) = """package $packageName

data class ErrorDTO(
    val error: String,
    val message: String,
    val statusCode: String
)
"""

