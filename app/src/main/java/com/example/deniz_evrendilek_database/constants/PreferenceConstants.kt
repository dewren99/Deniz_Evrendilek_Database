package com.example.deniz_evrendilek_database.constants

import android.content.Context
import androidx.preference.PreferenceManager

object PreferenceConstants {
    const val UNIT_PREFERENCE_KEY = "UNIT_PREFERENCE"
    const val UNIT_PREFERENCE_METRIC = "METRIC"
    const val UNIT_PREFERENCE_IMPERIAL = "IMPERIAL"
    private const val UNIT_PREFERENCE_DEFAULT = UNIT_PREFERENCE_IMPERIAL

    private fun milesToKm(miles: Double): Double {
        return miles * 1.609
    }

    fun metricValue(context: Context, value: Double): String {
        val pm = PreferenceManager.getDefaultSharedPreferences(context)
        val unit =
            pm.getString(UNIT_PREFERENCE_KEY, UNIT_PREFERENCE_DEFAULT) ?: UNIT_PREFERENCE_DEFAULT

        val convertedValue = if (unit == UNIT_PREFERENCE_METRIC) milesToKm(value) else value
        val prefix = if (unit == UNIT_PREFERENCE_METRIC) "Kilometers" else "Miles"

        return "$convertedValue $prefix"
    }
}