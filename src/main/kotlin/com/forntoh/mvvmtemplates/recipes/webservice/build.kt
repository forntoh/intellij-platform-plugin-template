package com.forntoh.mvvmtemplates.recipes.webservice

fun gradleBuildWebService(
    commonModuleName: String,
) = """plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android'
    id 'org.jetbrains.kotlin.kapt'
    id 'dagger.hilt.android.plugin'
}

Properties appProps = new Properties()
appProps.load(new FileInputStream(file('../app.properties')))

android {
    compileSdkVersion appProps['targetSdk'].toInteger()
    buildToolsVersion appProps['buildTools']

    defaultConfig {
        minSdkVersion appProps['minSdk'].toInteger()
    }

    buildTypes {
        release {
            minifyEnabled false
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation project(path: ':$commonModuleName')

    // Retrofit
    api "com.squareup.retrofit2:retrofit:+retrofit_version"
    implementation "com.squareup.retrofit2:converter-gson:+retrofit_version"
    implementation "com.squareup.retrofit2:converter-scalars:+retrofit_version"

    // OkHttp
    implementation "com.squareup.okhttp3:okhttp:+ok_http_Version"
    api "com.squareup.okhttp3:logging-interceptor:+ok_http_Version"

    // Hilt Dependency Injection
    implementation "com.google.dagger:hilt-android:+hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:+hilt_version"
    kapt "androidx.hilt:hilt-compiler:+hilt_android_version"

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "org.jetbrains.kotlin:kotlin-stdlib:+kotlin_version"
}
 """.replace('+', '$')