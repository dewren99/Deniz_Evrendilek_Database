package com.example.deniz_evrendilek_database.ui.fragments.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModel
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModelFactory

class HistoryFragment : Fragment() {
    private lateinit var view: View
    private lateinit var exerciseEntryViewModelFactory: ExerciseEntryViewModelFactory
    private lateinit var exerciseEntryViewModel: ExerciseEntryViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_history, container, false)

        exerciseEntryViewModelFactory = ExerciseEntryViewModelFactory(requireActivity())
        exerciseEntryViewModel = ViewModelProvider(
            requireActivity(),
            exerciseEntryViewModelFactory
        )[ExerciseEntryViewModel::class.java]

        exerciseEntryViewModel.allExerciseEntries.observe(viewLifecycleOwner) {
            println("History has ${it.size} entries")
        }
        return view
    }
}