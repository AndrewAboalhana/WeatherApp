package com.example.weatherapp.ui.base.fragments.all.start_fragment

import com.example.weatherapp.model.data.WeatherData

interface IStartView {

    fun showLoading()
    fun hideLoading()
    fun onStartSuccess()
    fun onStartFailure(error: String)
    fun navigateToDetailsFragment(data: WeatherData)
}