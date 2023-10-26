package com.example.deniz_evrendilek_database.ui.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.deniz_evrendilek_database.data.database.MainDatabase
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import com.example.deniz_evrendilek_database.data.repository.ExerciseEntryRepository
import kotlinx.coroutines.launch

class ExerciseEntryViewModel(
    context: Context
) : ViewModel() {
    private val _exerciseEntryRepository: ExerciseEntryRepository
    private val _allExerciseEntries: LiveData<List<ExerciseEntry>>

    init {
        val db = MainDatabase.getInstance(context)
        val dao = db.exerciseEntryDao
        _exerciseEntryRepository = ExerciseEntryRepository(dao)
        _allExerciseEntries = _exerciseEntryRepository.getAll().asLiveData()
    }

    val allExerciseEntries get() = _allExerciseEntries


    fun insert(exerciseEntry: ExerciseEntry) {
        _exerciseEntryRepository.insert(exerciseEntry)
    }

    fun deleteAll() {
        val liveData = _allExerciseEntries.value
        if (liveData.isNullOrEmpty()) {
            return
        }
        viewModelScope.launch {
            _exerciseEntryRepository.deleteAll()
        }
    }

}