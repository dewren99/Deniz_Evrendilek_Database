package com.example.deniz_evrendilek_database.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.deniz_evrendilek_database.data.database.MainDatabase
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import com.example.deniz_evrendilek_database.data.repository.ExerciseEntryRepository

class ExerciseEntryViewModel(
    context: Context
) : ViewModel() {
    private val _exerciseEntryRepository: ExerciseEntryRepository
    private val _allExerciseEntries: LiveData<List<ExerciseEntry>>
    private var _exerciseEntryDisplay: MutableLiveData<ExerciseEntry>?

    init {
        val db = MainDatabase.getInstance(context)
        val dao = db.exerciseEntryDao
        _exerciseEntryRepository = ExerciseEntryRepository(dao)
        _allExerciseEntries = _exerciseEntryRepository.getAll().asLiveData()
        _exerciseEntryDisplay = MutableLiveData()
    }

    val allExerciseEntries get() = _allExerciseEntries
    val exerciseEntryDisplay get() = _exerciseEntryDisplay


    fun insert(exerciseEntry: ExerciseEntry) {
        _exerciseEntryRepository.insert(exerciseEntry)
    }

    fun delete(exerciseEntry: ExerciseEntry) {
        _exerciseEntryRepository.delete(exerciseEntry)
    }

    fun display(exerciseEntry: ExerciseEntry) {
        _exerciseEntryDisplay = MutableLiveData(exerciseEntry)
    }

}