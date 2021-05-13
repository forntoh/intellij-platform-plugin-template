package com.forntoh.mvvmtemplates.setup

import com.android.tools.idea.wizard.template.RecipeExecutor

fun RecipeExecutor.commonDependencies() {
    addDependency("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2", "api")
    addDependency("androidx.legacy:legacy-support-v4:1.0.0", "api")
    addDependency("androidx.appcompat:appcompat:1.2.0", "api")
    addDependency("com.jakewharton.threetenabp:threetenabp:1.3.0", "api")
    addDependency("com.google.code.gson:gson:2.8.6", "api")
}

fun RecipeExecutor.hiltDependencies() {
    addDependency("com.google.dagger:hilt-android:\$hilt_version")
    addDependency("com.google.dagger:hilt-android-compiler:\$hilt_version", "kapt")
    addDependency("androidx.hilt:hilt-compiler:\$hilt_android_version", "kapt")
}

fun RecipeExecutor.roomDependencies() {
    addDependency("androidx.room:room-runtime:\$room_version")
    addDependency("androidx.room:room-ktx:\$room_version", "api")
    addDependency("androidx.room:room-compiler:\$room_version", "kapt")
}

fun RecipeExecutor.retrofitDependencies() {
    addDependency("com.squareup.retrofit2:retrofit:\$retrofit_version", "api")
    addDependency("com.squareup.retrofit2:converter-gson:\$retrofit_version")
    addDependency("com.squareup.retrofit2:converter-scalars:\$retrofit_version")
}

fun RecipeExecutor.okHttpDependencies() {
    addDependency("com.squareup.okhttp3:okhttp:\$ok_http_Version")
    addDependency("com.squareup.okhttp3:logging-interceptor:\$ok_http_Version", "api")
}