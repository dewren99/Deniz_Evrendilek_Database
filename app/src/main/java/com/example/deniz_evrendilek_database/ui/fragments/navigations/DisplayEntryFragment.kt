package com.example.deniz_evrendilek_database.ui.fragments.navigations

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.constants.ExerciseTypes
import com.example.deniz_evrendilek_database.constants.InputTypes
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import com.example.deniz_evrendilek_database.data.model.ManualExerciseEntryForm
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModel
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModelFactory


class DisplayEntryFragment : Fragment() {
    private lateinit var view: View
    private lateinit var exerciseEntryViewModelFactory: ExerciseEntryViewModelFactory
    private lateinit var exerciseEntryViewModel: ExerciseEntryViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_display_entry, container, false)

        exerciseEntryViewModelFactory = ExerciseEntryViewModelFactory(requireActivity())
        exerciseEntryViewModel = ViewModelProvider(
            requireActivity(), exerciseEntryViewModelFactory
        )[ExerciseEntryViewModel::class.java]

        exerciseEntryViewModel.exerciseEntryDisplay?.observe(viewLifecycleOwner) {
            fillTextViews(it)
        }
        return view
    }

    private fun fillTextViews(it: ExerciseEntry) {
        val inputType = InputTypes.getString(it.inputType)
        val exerciseType = ExerciseTypes.getString(it.activityType)
        val dateTime = ManualExerciseEntryForm.getDateTimeStr(it)
        val duration = ManualExerciseEntryForm.getDurationStr(it)
        val distance = ManualExerciseEntryForm.getDistanceStr(requireContext(), it)
        val calories = ManualExerciseEntryForm.getCaloriesStr(it)
        val heartRate = ManualExerciseEntryForm.getHeartRateStr(it)
        view.findViewById<TextView>(R.id.display_exercise_entry_input_type).text = inputType
        view.findViewById<TextView>(R.id.display_exercise_entry_exercise_type).text =
            exerciseType
        view.findViewById<TextView>(R.id.display_exercise_entry_date_time).text = dateTime
        view.findViewById<TextView>(R.id.display_exercise_entry_duration).text = duration
        view.findViewById<TextView>(R.id.display_exercise_entry_distance).text = distance
        view.findViewById<TextView>(R.id.display_exercise_entry_calories).text = calories
        view.findViewById<TextView>(R.id.display_exercise_entry_heart_rate).text = heartRate
    }
}