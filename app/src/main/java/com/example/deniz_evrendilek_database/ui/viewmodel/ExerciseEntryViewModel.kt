package com.example.deniz_evrendilek_database.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import com.example.deniz_evrendilek_database.data.repository.ExerciseEntryRepository

class ExerciseEntryViewModel(
    private val exerciseEntryRepository: ExerciseEntryRepository
) {
    private val allExerciseEntries: LiveData<List<ExerciseEntry>> =
        exerciseEntryRepository.allExerciseEntries.asLiveData()

    suspend fun insert(exerciseEntry: ExerciseEntry) {
        exerciseEntryRepository.insert(exerciseEntry)
    }

    suspend fun deleteAll() {
        val liveData = allExerciseEntries.value
        if (!liveData.isNullOrEmpty()) {
            exerciseEntryRepository.deleteAll()
        }
    }

}