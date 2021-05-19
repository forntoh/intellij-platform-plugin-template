package com.forntoh.mvvmtemplates.recipes.app

fun gradleBuildApp(appPackage: String, repoModuleName: String) = """plugins {
    id 'com.android.application'
    id 'org.jetbrains.kotlin.android'
    id 'org.jetbrains.kotlin.kapt'
    id 'androidx.navigation.safeargs.kotlin'
    id 'dagger.hilt.android.plugin'
}

Properties appProps = new Properties()
appProps.load(new FileInputStream(file('../app.properties')))

Properties versionProps = new Properties()
versionProps.load(new FileInputStream(file('version.properties')))

android {
    compileSdkVersion appProps['targetSdk'].toInteger()
    buildToolsVersion appProps['buildTools']

    defaultConfig {
        applicationId "$appPackage"
        minSdkVersion appProps['minSdk'].toInteger()
        targetSdkVersion appProps['targetSdk'].toInteger()
        versionCode versionProps['VERSION_CODE'].toInteger()
        versionName versionProps['VERSION_NAME']
        testInstrumentationRunner "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            minifyEnabled true
            proguardFiles getDefaultProguardFile('proguard-android-optimize.txt'), 'proguard-rules.pro'
        }
    }
    compileOptions {
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = '1.8'
        freeCompilerArgs += "-Xopt-in=kotlin.RequiresOptIn"
    }
    buildFeatures {
        dataBinding true
    }
}

dependencies {
    implementation project(path: ':$repoModuleName')

    implementation fileTree(dir: "libs", include: ["*.jar"])
    implementation "org.jetbrains.kotlin:kotlin-stdlib:~kotlin_version"
    implementation 'androidx.core:core-ktx:1.3.2'
    implementation 'androidx.constraintlayout:constraintlayout:2.0.4'
    implementation 'androidx.fragment:fragment-ktx:1.3.1'

    // Hilt Dependency Injection
    implementation "com.google.dagger:hilt-android:~hilt_version"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha03"
    implementation "androidx.hilt:hilt-work:~hilt_android_version"
    kapt "com.google.dagger:hilt-android-compiler:~hilt_version"
    kapt "androidx.hilt:hilt-compiler:~hilt_android_version"

    // New Material Design
    implementation "com.google.android.material:material:1.4.0-alpha01"

    // Navigation Library
    implementation "androidx.navigation:navigation-fragment-ktx:~arch_navigation"
    implementation "androidx.navigation:navigation-ui-ktx:~arch_navigation"

    // Lifecycle
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:~lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:~lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-service:~lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-common-java8:~lifecycle_version"

    // Material Dialogs
    implementation "com.afollestad.material-dialogs:core:~material_dialogs_version"
    implementation "com.afollestad.material-dialogs:lifecycle:~material_dialogs_version"

    // Groupie
    implementation "com.github.lisawray.groupie:groupie:~groupie_version"
    implementation "com.github.lisawray.groupie:groupie-viewbinding:~groupie_version"
    implementation "com.github.lisawray.groupie:groupie-kotlin-android-extensions:~groupie_version"

    // Work manager
    // implementation "androidx.work:work-runtime:~work_manager_version"
    // implementation "androidx.work:work-runtime-ktx:~work_manager_version"
    // implementation "androidx.work:work-gcm:~work_manager_version"
    // androidTestImplementation "androidx.work:work-testing:~work_manager_version"

    // Coil for image loading
    implementation 'io.coil-kt:coil-base:1.1.1'

    // Pretty State View
    // implementation 'com.github.Rohyme:PrettyStateView:3.0.0'

    // Data-binding live-data validation
    // implementation 'com.forntoh:android-livedata-validation:1.1.1'

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.2'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.3.0'

}""".replace('~', '$')

const val versionProps = """VERSION_NAME=v1.0.0
VERSION_CODE=1"""

const val appProps = """minSdk=21
targetSdk=30
buildTools=30.0.3"""