package com.example.weatherapp.ui.base.fragments.all.start_fragment

import StartPresenter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.R
import com.example.weatherapp.model.data.WeatherData
import com.example.weatherapp.ui.base.base.BaseFragment
import com.example.weatherapp.databinding.FragmentStartBinding
import com.example.weatherapp.ui.base.fragments.all.details_fragment.DetailsFragment
class StartFragment : BaseFragment<FragmentStartBinding>(), IStartView {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInfilater: (LayoutInflater, ViewGroup, Boolean) ->
    FragmentStartBinding = FragmentStartBinding::inflate
    private lateinit var presenter: StartPresenter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = StartPresenter(this)
        binding.buttonExplore.setOnClickListener {
            val country = binding.editTextSearch.text.toString()
            presenter.start(country)
        }
    }


    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }


    override fun showLoading() {
        Log.v(LOG_TAG, "showLoading")
    }

    override fun hideLoading() {
        Log.v(LOG_TAG, "hideLoading")
    }

    override fun onStartSuccess() {
        Log.v(LOG_TAG, "onStartSuccess")
    }

    override fun onStartFailure(error: String) {
        Log.v(LOG_TAG, "onStartFailure $error")
    }

    override fun navigateToDetailsFragment(data: WeatherData) {
        navigateToFragment(DetailsFragment.newInstance(data))
    }


}
