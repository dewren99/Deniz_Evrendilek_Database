package com.example.deniz_evrendilek_database.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.example.deniz_evrendilek_database.R
import com.example.deniz_evrendilek_database.constants.ExerciseTypes
import com.example.deniz_evrendilek_database.constants.InputTypes
import com.example.deniz_evrendilek_database.ui.viewmodel.StartFragmentViewModel
import com.example.deniz_evrendilek_database.utils.DateTimeUtils

class MainActivity : AppCompatActivity() {
    private lateinit var startFragmentViewModel: StartFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initGlobal()

        val toolbar: Toolbar = findViewById(R.id.toolbar)
        toolbar.title = resources.getString(R.string.myruns)
        setSupportActionBar(toolbar)
    }

    private fun initGlobal() {
        InputTypes.init(this)
        ExerciseTypes.init(this)
        DateTimeUtils.init(this)
        startFragmentViewModel =
            ViewModelProvider(this)[StartFragmentViewModel::class.java]
    }
}