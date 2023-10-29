package com.example.deniz_evrendilek_database.data.model

import android.icu.util.Calendar
import com.example.deniz_evrendilek_database.utils.DateTimeUtils

class ManualExerciseEntryForm(
    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
    var month: Int = Calendar.getInstance().get(Calendar.MONTH),
    var year: Int = Calendar.getInstance().get(Calendar.YEAR),
    var hour: Int = Calendar.getInstance().get(Calendar.HOUR),
    var minute: Int = Calendar.getInstance().get(Calendar.MINUTE),
    var duration: Double = 0.0,
    var distance: Double = 0.0,
    var calories: Double = 0.0,
    var heartRate: Double = 0.0,
    var comment: String = ""
) {
    fun saveInstanceState(
        putString: (String, String?) -> Unit,
        putInt: (String, Int) -> Unit,
        putDouble: (String, Double) -> Unit
    ) {
        putInt(this.javaClass.name + "day", day)
        putInt(this.javaClass.name + "month", month)
        putInt(this.javaClass.name + "year", year)
        putInt(this.javaClass.name + "hour", hour)
        putInt(this.javaClass.name + "minute", minute)
        putDouble(this.javaClass.name + "duration", duration)
        putDouble(this.javaClass.name + "distance", distance)
        putDouble(this.javaClass.name + "calories", calories)
        putDouble(this.javaClass.name + "heartRate", heartRate)
        putString(this.javaClass.name + "comment", comment)
    }

    operator fun component1(): Int {
        return day
    }

    operator fun component2(): Int {
        return month
    }

    operator fun component3(): Int {
        return year
    }

    fun restoreSavedInstanceState(
        getString: (String, String) -> String,
        getInt: (String, Int) -> Int,
        getDouble: (String, Double) -> Double,
    ): ManualExerciseEntryForm {
        return ManualExerciseEntryForm(
            getInt(this.javaClass.name + "day", day),
            getInt(this.javaClass.name + "month", month),
            getInt(this.javaClass.name + "year", year),
            getInt(this.javaClass.name + "hour", hour),
            getInt(this.javaClass.name + "minute", minute),
            getDouble(this.javaClass.name + "duration", duration),
            getDouble(this.javaClass.name + "distance", distance),
            getDouble(this.javaClass.name + "calories", calories),
            getDouble(this.javaClass.name + "heartRate", heartRate),
            getString(this.javaClass.name + "comment", comment)
        )
    }

    override fun toString(): String {
        return super.toString() + " | $day, $month, $year, $hour, $minute"
    }

    fun getCalendar(): Calendar {
        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_MONTH, day)
            set(Calendar.MONTH, month)
            set(Calendar.YEAR, year)
            // hour
            set(Calendar.MINUTE, minute)
        }
        if (DateTimeUtils.is24HourFormat) {
            calendar.set(Calendar.HOUR_OF_DAY, hour)
        } else {
            calendar.set(Calendar.HOUR, hour)
        }
        return calendar
    }
}
