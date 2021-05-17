package com.forntoh.mvvmtemplates.recipes

fun gradleBuildProject() = """buildscript {
    ext {
        hilt_version = '2.33-beta'
        room_version = '2.4.0-alpha02'
        kotlin_version = '1.4.31'
        arch_navigation = '2.3.5'
        ok_http_Version = '5.0.0-alpha.2'
        groupie_version = '2.9.0'
        retrofit_version = '2.9.0'
        lifecycle_version = '2.3.1'
        work_manager_version = '2.5.0'
        hilt_android_version = '1.0.0'
        material_dialogs_version = '3.3.0'
    }
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:4.2.0'
        classpath "com.google.dagger:hilt-android-gradle-plugin:+hilt_version"
        classpath "androidx.navigation:navigation-safe-args-gradle-plugin:+arch_navigation"
    }
}

allprojects {
    repositories {
        google()
        maven { url "https://www.jitpack.io" }
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}""".replace('+', '$')

fun appManifest(packageName: String, appName: String) = """<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="$packageName">

    <uses-permission android:name="android.permission.INTERNET" />

    <application
        android:name=".App"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.$appName"
        android:usesCleartextTraffic="true">
        <activity
            android:name=".ui.MainActivity"
            android:theme="@style/Theme.$appName"
            android:windowSoftInputMode="adjustResize">

            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>"""

fun moduleManifest(packageName: String) = """<?xml version="1.0" encoding="utf-8"?>
<manifest package="$packageName" />"""