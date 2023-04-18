package com.example.weatherapp.model

import okhttp3.Call

interface ApiService {

    fun getCityData(country:String): Call
}