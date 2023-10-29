package com.example.deniz_evrendilek_database.ui.fragments

import android.content.DialogInterface
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.constants.ExerciseTypes
import com.example.deniz_evrendilek_database.constants.InputTypes
import com.example.deniz_evrendilek_database.data.model.ManualExerciseEntryForm
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import com.example.deniz_evrendilek_database.ui.fragments.dialogs.AlertDialogFragment
import com.example.deniz_evrendilek_database.ui.fragments.dialogs.DateListener
import com.example.deniz_evrendilek_database.ui.fragments.dialogs.DatePickerDialogFragment
import com.example.deniz_evrendilek_database.ui.fragments.dialogs.TimeListener
import com.example.deniz_evrendilek_database.ui.fragments.dialogs.TimePickerDialogFragment
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModel
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModelFactory
import com.example.deniz_evrendilek_database.ui.viewmodel.StartFragmentViewModel

val ENTRY_OPTIONS = arrayOf(
    "Date", "Time", "Duration", "Distance", "Calories", "Heart Rate", "Comment"
)

private const val NEGATIVE_BUTTON_TEXT = "CANCEL"
private const val POSITIVE_BUTTON_TEXT = "OK"

class EntryFragment : Fragment(), DateListener, TimeListener {
    private lateinit var view: View
    private lateinit var listView: ListView
    private lateinit var buttonSave: Button
    private lateinit var buttonCancel: Button
    private lateinit var startFragmentViewModel: StartFragmentViewModel
    private lateinit var exerciseEntryViewModelFactory: ExerciseEntryViewModelFactory
    private lateinit var exerciseEntryViewModel: ExerciseEntryViewModel

    private var manualExerciseEntryForm = ManualExerciseEntryForm()

    private var exerciseType: String? = null
    private var inputType: String? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        println("onCreateView")
        view = inflater.inflate(R.layout.fragment_entry, container, false)
        setupListView()

        exerciseEntryViewModelFactory = ExerciseEntryViewModelFactory(requireActivity())
        exerciseEntryViewModel = ViewModelProvider(
            requireActivity(),
            exerciseEntryViewModelFactory
        )[ExerciseEntryViewModel::class.java]


        startFragmentViewModel =
            ViewModelProvider(requireActivity())[StartFragmentViewModel::class.java]

        startFragmentViewModel.inputAndActivityType.observe(viewLifecycleOwner) {
            println("Pair: $it")
            inputType = it.first
            exerciseType = it.second
        }
        return view
    }

    override fun onSaveInstanceState(outState: Bundle) {
        println("onSaveInstanceState")
        super.onSaveInstanceState(outState)

        manualExerciseEntryForm.saveInstanceState(
            { key: String, value: String? -> outState.putString(key, value) },
            { key: String, value: Int -> outState.putInt(key, value) },
            { key: String, value: Double -> outState.putDouble(key, value) }
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        println("onViewCreated")
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState == null) {
            return
        }

        val restoredData =
            manualExerciseEntryForm.restoreSavedInstanceState(
                { key: String, defaultValue: String ->
                    savedInstanceState.getString(key, defaultValue)
                },
                { key: String,
                  defaultValue: Int ->
                    savedInstanceState.getInt(key, defaultValue)
                },
                { key: String,
                  defaultValue: Double ->
                    savedInstanceState.getDouble(key, defaultValue)
                }
            )
        println(restoredData)
        manualExerciseEntryForm = restoredData
    }

    private fun onSave() {
        if (inputType == null || exerciseType == null) {
            throw IllegalStateException("inputType or exerciseType  is null after navigation")
        }
        val inputTypeId = InputTypes.getId(inputType!!)
        val exerciseTypeId = ExerciseTypes.getId(exerciseType!!)
        if (inputTypeId == null || exerciseTypeId == null) {
            throw IllegalStateException("inputType id or exerciseType id is null after navigation")
        }
        val entry = ExerciseEntry(
            inputType = inputTypeId,
            activityType = exerciseTypeId,
            dateTime = manualExerciseEntryForm.getCalendar(),
            duration = manualExerciseEntryForm.duration,
            distance = manualExerciseEntryForm.distance,
            avgPace = 0.0, // TODO
            avgSpeed = 0.0, // TODO
            calorie = manualExerciseEntryForm.calories,
            climb = 0.0, // TODO
            heartRate = manualExerciseEntryForm.heartRate,
            comment = manualExerciseEntryForm.comment,
            locationList = arrayListOf(), // TODO
        )
        exerciseEntryViewModel.insert(entry)
    }

    private fun handleOnItemClickListener(selected: String) {
        when (selected) {
            ENTRY_OPTIONS[0] -> createAndShowDatePicker()
            ENTRY_OPTIONS[1] -> createAndShowTimePicker()
            ENTRY_OPTIONS[2] -> createAndShowDurationDialog()
            ENTRY_OPTIONS[3] -> createAndShowDistanceDialog()
            ENTRY_OPTIONS[4] -> createAndShowCaloriesDialog()
            ENTRY_OPTIONS[5] -> createAndShowHeartRateDialog()
            ENTRY_OPTIONS[6] -> createAndShowCommentDialog()
            else -> throw IllegalStateException("Unexpected listView selection: $selected")
        }
    }

    private fun setupListView() {
        listView = view.findViewById(R.id.list_view)
        listView.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, ENTRY_OPTIONS)

        listView.setOnItemClickListener { parent, _, position, _ ->
            val selected = parent.getItemAtPosition(position) as String
            println(selected)

            handleOnItemClickListener(selected)
        }

        buttonSave = view.findViewById(R.id.manual_entry_save_button)
        buttonCancel = view.findViewById(R.id.manual_entry_cancel_button)
        buttonSave.setOnClickListener {
            onSave()
            findNavController().navigate(R.id.action_entryFragment_to_mainFragment)
        }
        buttonCancel.setOnClickListener {
            findNavController().navigate(R.id.action_entryFragment_to_mainFragment)
            Toast.makeText(requireContext(), "Entry discarded", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createAndShowDatePicker() {
        val (day, month, year) = manualExerciseEntryForm
        val datePickerDialog = DatePickerDialogFragment(
            year, month, day
        )
        @Suppress("DEPRECATION") datePickerDialog.setTargetFragment(this, 0)
        datePickerDialog.show(parentFragmentManager, "datePicker")
    }

    private fun createAndShowTimePicker() {
        val timePickerDialogFragment =
            TimePickerDialogFragment(manualExerciseEntryForm.hour, manualExerciseEntryForm.minute)
        @Suppress("DEPRECATION") timePickerDialogFragment.setTargetFragment(this, 0)
        timePickerDialogFragment.show(parentFragmentManager, "timePicker")
    }

    @Suppress("SameParameterValue")
    private fun createAndShowAlertDialog(
        title: String,
        inputType: Int,
        placeHolder: String?,
        positiveButtonText: String,
        negativeButtonText: String,
        positiveButtonCallback: (DialogInterface, Int) -> Unit,
        negativeButtonCallback: (DialogInterface, Int) -> Unit
    ) {
        val alertDialogFragment = AlertDialogFragment(
            title,
            inputType,
            placeHolder,
            positiveButtonText,
            negativeButtonText,
            positiveButtonCallback,
            negativeButtonCallback,
            null
        )
        alertDialogFragment.show(childFragmentManager, title)
    }

    private fun createAndShowDurationDialog() {
        createAndShowAlertDialog(ENTRY_OPTIONS[2],
            InputType.TYPE_CLASS_NUMBER,
            null,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT,
            { _, _ -> println("OK") },
            { _, _ -> println("CANCEL") })
    }

    private fun createAndShowDistanceDialog() {
        createAndShowAlertDialog(ENTRY_OPTIONS[3],
            InputType.TYPE_CLASS_NUMBER,
            null,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT,
            { _, _ -> println("OK") },
            { _, _ -> println("CANCEL") })
    }

    private fun createAndShowCaloriesDialog() {
        createAndShowAlertDialog(ENTRY_OPTIONS[4],
            InputType.TYPE_CLASS_NUMBER,
            null,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT,
            { _, _ -> println("OK") },
            { _, _ -> println("CANCEL") })
    }

    private fun createAndShowHeartRateDialog() {
        createAndShowAlertDialog(ENTRY_OPTIONS[5],
            InputType.TYPE_CLASS_NUMBER,
            null,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT,
            { _, _ -> println("OK") },
            { _, _ -> println("CANCEL") })
    }

    private fun createAndShowCommentDialog() {
        val hint = "How did it go? Notes here."
        createAndShowAlertDialog(ENTRY_OPTIONS[6],
            InputType.TYPE_TEXT_FLAG_MULTI_LINE,
            hint,
            POSITIVE_BUTTON_TEXT,
            NEGATIVE_BUTTON_TEXT,
            { _, _ -> println("OK") },
            { _, _ -> println("CANCEL") })
    }

    override fun onDateSelected(year: Int, month: Int, dayOfMonth: Int) {
        manualExerciseEntryForm.year = year
        manualExerciseEntryForm.month = month
        manualExerciseEntryForm.day = dayOfMonth
    }

    override fun onTimeSelected(hourOfDay: Int, minute: Int) {
        manualExerciseEntryForm.hour = hourOfDay
        manualExerciseEntryForm.minute = minute
        println("$hourOfDay:$minute")
    }

}