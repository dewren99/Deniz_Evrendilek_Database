package com.example.deniz_evrendilek_database.ui.adapters

import android.content.Context
import android.icu.text.SimpleDateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.constants.ExerciseTypes
import com.example.deniz_evrendilek_database.constants.InputTypes
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import java.util.Locale

private const val DATE_TIME_FORMAT = "HH:mm:ss MMM dd yyyy"

class ListViewAdapter(
    private val context: Context, private var exerciseEntryList: Array<ExerciseEntry>
) : ArrayAdapter<ExerciseEntry>(context, R.layout.history_item, exerciseEntryList) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view =
            convertView ?: LayoutInflater.from(context)
                .inflate(R.layout.history_item, parent, false)

        val item = exerciseEntryList[position]
        val inputType = InputTypes.getString(item.inputType)
        val exerciseType = ExerciseTypes.getString(item.activityType)
        val sdf = SimpleDateFormat(DATE_TIME_FORMAT, Locale.getDefault())
        val dateTime = sdf.format(item.dateTime)

        view.findViewById<TextView>(R.id.history_item_input_type).text = inputType
        view.findViewById<TextView>(R.id.history_item_exercise_type).text = exerciseType
        view.findViewById<TextView>(R.id.history_item_date_time).text = dateTime
        view.findViewById<TextView>(R.id.history_item_duration).text = item.duration.toString()
        view.findViewById<TextView>(R.id.history_item_distance).text = item.distance.toString()
//        view.findViewById<TextView>(R.id.history_item_avg_pace).text = item.avgPace.toString()
//        view.findViewById<TextView>(R.id.history_item_avg_speed).text = item.avgSpeed.toString()
        view.findViewById<TextView>(R.id.history_item_calorie).text = item.calorie.toString()
//        view.findViewById<TextView>(R.id.history_item_climb).text = item.climb.toString()
        view.findViewById<TextView>(R.id.history_item_heart_rate).text = item.heartRate.toString()
//        view.findViewById<TextView>(R.id.history_item_comment).text = item.comment
//        view.findViewById<TextView>(R.id.history_item_location).text = item.locationList.toString()
        println("getView position: $position")
        return view
    }

    fun update(list: Array<ExerciseEntry>) {
        println("update ListView with size: ${list.size}")
        exerciseEntryList = list
        notifyDataSetChanged()
    }
}