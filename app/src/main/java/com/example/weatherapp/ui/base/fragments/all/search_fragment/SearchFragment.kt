package com.example.weatherapp.ui.base.fragments.all.search_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.model.data.WeatherData
import com.example.weatherapp.presenter.SearchPresenter
import com.example.weatherapp.ui.base.base.BaseFragment
import com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter.SearchAdapter


class SearchFragment : BaseFragment<FragmentSearchBinding>(),ISearchView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInfilater: (LayoutInflater, ViewGroup, Boolean) ->
    FragmentSearchBinding = FragmentSearchBinding::inflate
    private lateinit var presenter: SearchPresenter
    private var weatherData = listOf<WeatherData>()
    private var adapter = SearchAdapter(weatherData)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = SearchPresenter(this)
    }

    private fun displayTeamTasks(weatherData: List<WeatherData>) {
        val country = binding.editTextSearchHome.text
         presenter.getSearchData(country.toString())
        activity?.runOnUiThread {
            this.weatherData = weatherData
            adapter = SearchAdapter(weatherData)
            binding.recyclerViewSearch.adapter = adapter
            Log.i("TeamTasksFragment", weatherData.toString())
        }
    }

    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onSearchSuccess() {
        Log.v(LOG_TAG, "onSearchSuccess")

    }

    override fun onSearchFailure(error: String) {
        Log.v(LOG_TAG, "onSearchFailure $error")
    }

    override fun onTeamTasksSuccess(searchData: List<WeatherData>) {
        displayTeamTasks(searchData)
    }

}