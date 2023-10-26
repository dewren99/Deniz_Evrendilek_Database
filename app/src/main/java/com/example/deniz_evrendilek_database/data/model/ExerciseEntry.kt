package com.example.deniz_evrendilek_database.data.model

import android.icu.util.Calendar
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.android.gms.maps.model.LatLng

@Entity(tableName = "exercise_entry_table")
data class ExerciseEntry(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "exercise_input_type")
    val inputType: Int,                // Manual, GPS or automatic
    @ColumnInfo(name = "exercise_type")
    val activityType: Int,             // Running, cycling etc.
    @ColumnInfo(name = "exercise_date_time")
    val dateTime: Calendar,            // When does this entry happen
    @ColumnInfo(name = "exercise_duration")
    val duration: Double,              // Exercise duration in seconds
    @ColumnInfo(name = "exercise_distance")
    val distance: Double,              // Distance traveled. Either in meters or feet.
    @ColumnInfo(name = "exercise_avg_pace")
    val avgPace: Double,               // Average pace
    @ColumnInfo(name = "exercise_avg_speed")
    val avgSpeed: Double,              // Average speed
    @ColumnInfo(name = "exercise_calorie")
    val calorie: Double,               // Calories burnt
    @ColumnInfo(name = "exercise_climb")
    val climb: Double,                 // Climb. Either in meters or feet.
    @ColumnInfo(name = "exercise_heart_rate")
    val heartRate: Double,             // Heart rate
    @ColumnInfo(name = "exercise_comment")
    val comment: String,               // Comments
    @ColumnInfo(name = "exercise_location_list")
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