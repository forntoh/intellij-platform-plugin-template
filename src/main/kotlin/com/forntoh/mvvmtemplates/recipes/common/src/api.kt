package com.forntoh.mvvmtemplates.recipes.common.src

fun preferenceRepository(
    packageName: String
) = """package $packageName

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import android.content.res.Resources
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

/**
 * A simple data repository for in-app settings.
 */
@Singleton
class PreferenceRepository @Inject constructor(@ApplicationContext private val context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences(context.packageName + ".DB", Context.MODE_PRIVATE)

    private val systemUiMode: Int
        get() = when (Resources.getSystem()?.configuration!!.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> AppCompatDelegate.MODE_NIGHT_YES
            else -> AppCompatDelegate.MODE_NIGHT_NO
        }

    private val nightMode: Int
        get() = if (useSystemUiMode) systemUiMode else sharedPreferences.getInt(
            PREFERENCE_NIGHT_MODE,
            PREFERENCE_NIGHT_MODE_DEF_VAL
        )

    var useSystemUiMode: Boolean = false
        get() = sharedPreferences.getBoolean(PREFERENCE_NIGHT_MODE_SYSTEM, false)
        set(value) {
            sharedPreferences.edit().putBoolean(PREFERENCE_NIGHT_MODE_SYSTEM, value).apply()
            field = value
            isDarkTheme = if (value) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
                systemUiMode == AppCompatDelegate.MODE_NIGHT_YES
            } else {
                AppCompatDelegate.setDefaultNightMode(nightMode)
                nightMode == AppCompatDelegate.MODE_NIGHT_YES
            }
        }

    private val _nightModeLive: MutableLiveData<Int> = MutableLiveData()
    val nightModeLive: LiveData<Int>
        get() = _nightModeLive

    var isDarkTheme: Boolean = false
        get() = nightMode == AppCompatDelegate.MODE_NIGHT_YES
        set(value) {
            sharedPreferences.edit().putInt(
                PREFERENCE_NIGHT_MODE,
                if (useSystemUiMode) systemUiMode else {
                    if (value)
                        AppCompatDelegate.MODE_NIGHT_YES
                    else
                        AppCompatDelegate.MODE_NIGHT_NO
                }
            ).apply()
            field = value
        }

    private val _isDarkThemeLive: MutableLiveData<Boolean> = MutableLiveData()
    val isDarkThemeLive: LiveData<Boolean>
        get() = _isDarkThemeLive
        
    var userToken: String? = null
        get() = loadString(PREFERENCE_USER_TOKEN)
        set(value) {
            saveString(PREFERENCE_USER_TOKEN, value)
            field = value
        }
        
    private val preferenceChangedListener =
        SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
            when (key) {
                PREFERENCE_NIGHT_MODE -> {
                    _nightModeLive.value = nightMode
                    _isDarkThemeLive.value = isDarkTheme
                }
            }
        }

    private fun saveString(key: String, value: String?) {
        if (value.isNullOrEmpty()) return
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
    }

    private fun loadString(key: String): String? = sharedPreferences.getString(key, null)

    init {
        _nightModeLive.value = nightMode
        _isDarkThemeLive.value = isDarkTheme

        sharedPreferences.registerOnSharedPreferenceChangeListener(preferenceChangedListener)
    }

    companion object {
        private const val PREFERENCE_USER_TOKEN = "token"
        private const val PREFERENCE_NIGHT_MODE = "preference_night_mode"
        private const val PREFERENCE_NIGHT_MODE_SYSTEM = "preference_night_mode_system"
        private const val PREFERENCE_NIGHT_MODE_DEF_VAL = AppCompatDelegate.MODE_NIGHT_NO
    }
}
"""