package com.example.weatherapp.ui.base.fragments.all.details_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.weatherapp.Constant
import com.example.weatherapp.R
import com.example.weatherapp.model.data.WeatherData
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.ui.base.base.BaseFragment
import com.example.weatherapp.ui.base.fragments.all.search_fragment.SearchFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>() {
    override val LOG_TAG: String = this::class.java.name
    override val bindingInfilater: (LayoutInflater, ViewGroup, Boolean) ->
    FragmentDetailsBinding = FragmentDetailsBinding::inflate
    var data: WeatherData? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        arguments?.let {
            data = it.getSerializable(Constant.DATA) as? WeatherData
            setDataToUi()
        }
        Log.i("test", data?.current?.temp_c.toString())
        binding.imageViewSearch.setOnClickListener {
            navigateToFragment(SearchFragment())
        }

    }

    private fun setDataToUi() {
        val summerClothes = listOf(
            R.drawable.summer,
            R.drawable.summer2,
            R.drawable.summer3,
            R.drawable.summer4,
            R.drawable.summer5,
            R.drawable.summer6,
            R.drawable.summer6,
            R.drawable.summer7,
            R.drawable.summer8,
            R.drawable.summer9,
            R.drawable.summer10
        )

        val winterClothes = listOf(
            R.drawable.winter, R.drawable.winter2, R.drawable.winter3,
            R.drawable.winter4, R.drawable.winter5, R.drawable.winter6, R.drawable.winter7,
            R.drawable.winter8, R.drawable.winter9, R.drawable.winter10
        )

        with(binding) {
            textViewCountry.text = data?.location?.country
            textViewCity.text = data?.location?.name
            textViewWeatherState.text = data?.current?.condition?.text
            textViewDate.text = data?.location?.localtime
            textViewWeatherDegree.text = data?.current?.temp_c.toString()

            cardViewWeatherStates.apply {
                textViewFeelsLikeDegree.text = data?.current?.feelslike_c.toString()
                textViewWindSpeed.text = data?.current?.wind_kph.toString()
                textViewUvPercent.text = data?.current?.uv.toString()
                textViewRainPercent.text = data?.current?.cloud.toString()
                textViewHumidityPercent.text = data?.current?.humidity.toString()
            }

            binding.imageViewWeather.setImageResource(
                when (data?.current?.condition?.text) {
                    SUNNY -> R.drawable._995001_cloud_cloudy_sun_weather_summer_icon
                    MIST -> R.drawable.mist
                    CLEAR -> R.drawable.sun
                    else -> R.drawable.default_day
                }
            )

            val temperature = data?.current?.temp_c ?: 0.0
            binding.imageViewClothes.setImageResource(
                when (temperature) {
                    in WINTER_RANGE -> winterClothes.shuffled()[0]
                    in SUMMER_RANGE -> summerClothes.shuffled()[0]
                    else -> R.drawable.summer
                }
            )
        }
    }


    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object {
        const val SUNNY = "Sunny"
        const val MIST = "Mist"
        const val CLEAR = "Clear"
        val WINTER_RANGE = -100.0..15.0
        val SUMMER_RANGE = 16.0..100.0
            fun newInstance(data: WeatherData) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constant.DATA, data)
            }
        }
    }
}