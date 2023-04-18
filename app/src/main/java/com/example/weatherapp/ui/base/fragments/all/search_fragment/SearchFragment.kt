package com.example.weatherapp.ui.base.fragments.all.search_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentSearchBinding
import com.example.weatherapp.model.data.WeatherData
import com.example.weatherapp.presenter.SearchPresenter
import com.example.weatherapp.ui.base.base.BaseFragment
import com.example.weatherapp.ui.base.fragments.all.details_fragment.DetailsFragment
import com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter.SearchAdapter
import com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter.SearchListInteraction


class SearchFragment : BaseFragment<FragmentSearchBinding>(),ISearchView,SearchListInteraction {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInfilater: (LayoutInflater, ViewGroup, Boolean) ->
    FragmentSearchBinding = FragmentSearchBinding::inflate
    private lateinit var presenter: SearchPresenter
    private var weatherData = listOf<WeatherData>()
    private var adapter = SearchAdapter(weatherData,this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = SearchPresenter(this)
        binding.textInputLayoutSearchHome.setEndIconOnClickListener {
            val country = binding.editTextSearchHome.text.toString()
            presenter.getSearchData(country)
        }
        binding.recyclerViewSearch.adapter = adapter
    }

    private fun displayTeamTasks(weatherData: List<WeatherData>) {
        activity?.runOnUiThread {
            this.weatherData = weatherData
            adapter.updateTasks(weatherData)
            Log.i(LOG_TAG, weatherData.toString())
        }
    }

    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    private fun showToastNotFoundError() {
        activity?.runOnUiThread {
            Toast.makeText(requireContext(), "City not found", Toast.LENGTH_SHORT).show()
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

    override fun showCityNotFoundError() {
        showToastNotFoundError()
    }


    override fun onSearchSuccess(searchData: List<WeatherData>) {
        displayTeamTasks(searchData)
    }

    override fun onClickSearchItem(weatherData: WeatherData) {
        navigateToFragment(DetailsFragment.newInstance(weatherData))
    }
}
