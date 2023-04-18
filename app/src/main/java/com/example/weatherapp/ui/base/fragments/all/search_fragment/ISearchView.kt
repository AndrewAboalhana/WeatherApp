package com.example.weatherapp.ui.base.fragments.all.search_fragment

import com.example.weatherapp.model.data.WeatherData

interface ISearchView {
    fun showLoading()
    fun hideLoading()
    fun onSearchSuccess()
    fun onSearchFailure(error: String)
    fun onTeamTasksSuccess(searchData: List<WeatherData>)
}