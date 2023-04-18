package com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter

import com.example.weatherapp.model.data.WeatherData

interface SearchListInteraction {

    fun onClickSearchItem(weatherData: WeatherData)
}