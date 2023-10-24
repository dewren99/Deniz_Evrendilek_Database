package com.example.deniz_evrendilek_database.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.data.database.MainDatabase
import com.example.deniz_evrendilek_database.data.repository.ExerciseEntryRepository
import com.example.deniz_evrendilek_database.ui.viewmodel.ExerciseEntryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var database: MainDatabase
    private lateinit var repository: ExerciseEntryRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initDB()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = resources.getString(R.string.myruns2)
        setSupportActionBar(toolbar)
    }

    private fun initDB() {
        database = MainDatabase.getInstance(this)
        repository = ExerciseEntryRepository(database.exerciseEntryDao)
        ExerciseEntryViewModel(repository)
    }
}