package com.forntoh.mvvmtemplates.recipes.repository

fun gradleBuildRepo(
    commonModuleName: String,
    databaseModuleName: String,
    webServiceModuleName: String,
) = """plugins {
    id 'com.android.library'
    id 'org.jetbrains.kotlin.android' version "+kotlin_version"
    id 'org.jetbrains.kotlin.kapt'
    id 'dagger.hilt.android.plugin'
}

Properties appProps = new Properties()
appProps.load(new FileInputStream(file('../app.properties')))

android {
    compileSdkVersion appProps['targetSdk'].toInteger()
    buildToolsVersion appProps['buildTools']

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
    api project(path: ':$commonModuleName')
    api project(path: ':$databaseModuleName')
    api project(path: ':$webServiceModuleName')

    // Hilt Dependency Injection
    implementation "com.google.dagger:hilt-android:+hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:+hilt_version"
    kapt "androidx.hilt:hilt-compiler:+hilt_android_version"

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "org.jetbrains.kotlin:kotlin-stdlib:+kotlin_version"
}
 """.replace('+', '$')