package com.forntoh.mvvmtemplates.recipes.database

fun gradleBuildDatabase(
    commonModuleName: String,
) = """plugins {
    id 'com.android.library'
    id 'kotlin-android'
    id 'kotlin-kapt'
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

    // Room Persistence Library
    implementation "androidx.room:room-runtime:+room_version"
    api "androidx.room:room-ktx:+room_version"
    kapt "androidx.room:room-compiler:+room_version"

    // Hilt Dependency Injection
    implementation "com.google.dagger:hilt-android:+hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:+hilt_version"
    kapt "androidx.hilt:hilt-compiler:+hilt_android_version"

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "org.jetbrains.kotlin:kotlin-stdlib:+kotlin_version"
}
 """.replace('+', '$')