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
import com.example.deniz_evrendilek_database.constants.PreferenceConstants
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
        val distance = PreferenceConstants.metricValue(context, item.distance)
        val title = "$inputType: $exerciseType, $dateTime"
        val text = "$distance ${item.duration}"

        view.findViewById<TextView>(R.id.history_item_title).text = title
        view.findViewById<TextView>(R.id.history_item_text).text = text
        return view
    }
}