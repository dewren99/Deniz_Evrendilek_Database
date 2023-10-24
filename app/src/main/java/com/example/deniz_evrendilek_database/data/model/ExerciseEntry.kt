package com.example.deniz_evrendilek_database.data.model

import android.icu.util.Calendar
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "exercise_entry_table")
data class ExerciseEntry(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    val inputType: Int,                // Manual, GPS or automatic
    val activityType: Int,             // Running, cycling etc.
    val dateTime: Calendar,            // When does this entry happen
    val duration: Double,              // Exercise duration in seconds
    val distance: Double,              // Distance traveled. Either in meters or feet.
    val avgPace: Double,               // Average pace
    val avgSpeed: Double,              // Average speed
    val calorie: Double,               // Calories burnt
    val climb: Double,                 // Climb. Either in meters or feet.
    val heartRate: Double,             // Heart rate
    val comment: String,               // Comments
    val locationList: ArrayList<LatLng>// Location list


//    var day: Int = Calendar.getInstance().get(Calendar.DAY_OF_MONTH),
//    var month: Int = Calendar.getInstance().get(Calendar.MONTH),
//    var year: Int = Calendar.getInstance().get(Calendar.YEAR),
//    var hour: Int = Calendar.getInstance().get(Calendar.HOUR),
//    var minute: Int = Calendar.getInstance().get(Calendar.MINUTE),
//    private var duration: Int = 0,
//    private var distance: Int = 0,
//    private var calories: Int = 0,
//    private var heartRate: Int = 0,
//    private var comment: String = ""
)