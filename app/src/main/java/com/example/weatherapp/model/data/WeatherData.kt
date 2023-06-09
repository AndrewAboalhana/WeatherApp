package com.example.weatherapp.model.data

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class WeatherData(
    @SerializedName("location")
    val location: Location,
    @SerializedName("current")
    val current: Current,
):Serializable
