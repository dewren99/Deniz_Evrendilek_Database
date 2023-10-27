package com.example.deniz_evrendilek_database.ui.fragments.tabs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.ui.adapters.ListViewAdapter
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModel
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModelFactory

class HistoryFragment : Fragment() {
    private lateinit var view: View
    private lateinit var exerciseEntryViewModelFactory: ExerciseEntryViewModelFactory
    private lateinit var exerciseEntryViewModel: ExerciseEntryViewModel
    private lateinit var listView: ListView
    private lateinit var listViewAdapter: ListViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view = inflater.inflate(R.layout.fragment_history, container, false)
        listView = view.findViewById(R.id.history_list_view)
        listViewAdapter = ListViewAdapter(requireContext(), emptyArray())
        listView.adapter = listViewAdapter

        exerciseEntryViewModelFactory = ExerciseEntryViewModelFactory(requireActivity())
        exerciseEntryViewModel = ViewModelProvider(
            requireActivity(),
            exerciseEntryViewModelFactory
        )[ExerciseEntryViewModel::class.java]

        exerciseEntryViewModel.allExerciseEntries.observe(viewLifecycleOwner) {
            println("History has ${it.size} entries")
            it.forEach { item -> println(item) }
            listViewAdapter = ListViewAdapter(requireContext(), it.toTypedArray())
            listView.adapter = listViewAdapter
        }
        return view
    }
}