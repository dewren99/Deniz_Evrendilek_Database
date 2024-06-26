package com.example.deniz_evrendilek_database.data.repository

import com.example.deniz_evrendilek_database.data.dao.ExerciseEntryDao
import com.example.deniz_evrendilek_database.data.model.ExerciseEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class ExerciseEntryRepository(
    private val exerciseEntryDao: ExerciseEntryDao
) {
    private val _allExerciseEntries: Flow<List<ExerciseEntry>> = exerciseEntryDao.getAll()

    fun getAll(): Flow<List<ExerciseEntry>> {
        return _allExerciseEntries
    }

    fun insert(exerciseEntry: ExerciseEntry) {
        CoroutineScope(IO).launch {
            exerciseEntryDao.insert(exerciseEntry)
        }
    }

    // TODO: add delete(id)
    fun delete(exerciseEntry: ExerciseEntry) {
        CoroutineScope(IO).launch {
            exerciseEntryDao.delete(exerciseEntry)
        }
    }

    fun deleteAll() {
        CoroutineScope(IO).launch {
            exerciseEntryDao.deleteAll()
        }
    }
}