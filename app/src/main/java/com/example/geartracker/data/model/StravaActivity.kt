package com.example.geartracker.data.model

data class StravaActivity(
    val id: Long,
    val name: String,
    val distance: Double,
    val moving_time: Int,
    val elapsed_time: Int,
    val total_elevation_gain: Double,
    val type: String,
    val start_date: String
)