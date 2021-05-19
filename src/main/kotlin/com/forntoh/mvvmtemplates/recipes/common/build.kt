package com.forntoh.mvvmtemplates.recipes.common

fun gradleBuildCommon() = """plugins {
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
        targetSdkVersion appProps['targetSdk'].toInteger()
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
    // Coroutines
    api 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'

    api 'androidx.legacy:legacy-support-v4:1.0.0'

    api 'androidx.appcompat:appcompat:1.2.0'

    api 'com.jakewharton.threetenabp:threetenabp:1.3.0'

    api 'com.google.code.gson:gson:2.8.6'

    api platform('com.google.firebase:firebase-bom:26.2.0')
    api 'com.google.firebase:firebase-analytics-ktx'
    api 'com.google.firebase:firebase-crashlytics-ktx'
    api 'com.google.firebase:firebase-messaging-ktx'

    // Hilt Dependency Injection
    implementation "com.google.dagger:hilt-android:+hilt_version"
    kapt "com.google.dagger:hilt-android-compiler:+hilt_version"
    kapt "androidx.hilt:hilt-compiler:+hilt_android_version"

    implementation "androidx.room:room-common:+room_version"

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "org.jetbrains.kotlin:kotlin-stdlib:+kotlin_version"
}
 """.replace('+', '$')