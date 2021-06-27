package com.ducnguyen2102.baseclean.data.local.prefs

import android.content.SharedPreferences
import com.google.gson.Gson
import javax.inject.Inject

class AppPrefsImpl @Inject constructor(
    private val pref: SharedPreferences,
    private val gson: Gson
) : AppPrefs