package com.example.deniz_evrendilek_database.constants

import android.content.Context
import com.example.deniz_evrendilek_database.R

object ExerciseTypes {
    private lateinit var _types: Array<String>
    private lateinit var _typesWithIntIds: Map<String, Int>
    fun init(context: Context) {
        _types = context.resources.getStringArray(R.array.ActivityType)
        val temp = mutableMapOf<String, Int>()
        var i = 1
        _types.forEach {
            temp[it] = i
            i++
        }
        _typesWithIntIds = temp
    }

    fun getAll() = _types
    fun getString(index: Int) = _types[index]
    fun getId(type: String) = _typesWithIntIds[type]
}