package com.example.weatherapp.ui.base.fragments.all.details_fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
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
        Log.i("test",data?.current?.temp_c.toString())
       binding.imageViewSearch.setOnClickListener {
           navigateToFragment(SearchFragment())
       }

    }

    private fun setDataToUi(){
        val summerClothes = listOf(R.drawable.summer,R.drawable.summer2,R.drawable.summer3,
       R.drawable.summer4,R.drawable.summer5,R.drawable.summer6,R.drawable.summer6,R.drawable.summer7
        ,R.drawable.summer8,R.drawable.summer9,R.drawable.summer10)
        val winterClothes = listOf(R.drawable.winter,R.drawable.winter2,R.drawable.winter3,
        R.drawable.winter4,R.drawable.winter5,R.drawable.winter6,R.drawable.winter7
        ,R.drawable.winter8,R.drawable.winter9,R.drawable.winter10)
        val bindingCard = binding.cardViewWeatherStates
        binding.textViewCountry.text = data?.location?.country
        binding.textViewCity.text = data?.location?.name
        binding.textViewWeatherState.text = data?.current?.condition?.text
        binding.textViewDate.text = data?.location?.localtime
        binding.textViewWeatherDegree.text = data?.current?.temp_c.toString()
        bindingCard.textViewFeelsLikeDegree.text = data?.current?.feelslike_c.toString()
        bindingCard.textViewWindSpeed.text = data?.current?.wind_kph.toString()
        bindingCard.textViewUvPercent.text = data?.current?.uv.toString()
        bindingCard.textViewRainPercent.text = data?.current?.cloud.toString()
        bindingCard.textViewHumidityPercent.text = data?.current?.humidity.toString()
        when(data?.current?.condition?.text){
            "Sunny" -> binding.imageViewWeather.setImageResource(R.drawable._995001_cloud_cloudy_sun_weather_summer_icon)
            "Mist" -> binding.imageViewWeather.setImageResource(R.drawable.mist)
            "Clear" -> binding.imageViewWeather.setImageResource(R.drawable.sun)
            else -> binding.imageViewWeather.setImageResource(R.drawable.default_day)

        }
        when(data?.current?.temp_c!!){
            in -100.0..15.0 ->{ binding.imageViewClothes.setImageResource(winterClothes.shuffled()[0])}
            in 16.0..100.0 ->{ binding.imageViewClothes.setImageResource(summerClothes.shuffled()[0])}
                else -> {
                    binding.imageViewClothes.setImageResource(R.drawable.summer)
                }

        }

    }


    private fun navigateToFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(null)
            commit()
        }
    }

    companion object{
        fun newInstance(data: WeatherData) = DetailsFragment().apply {
            arguments = Bundle().apply {
                putSerializable(Constant.DATA,data)
            }
        }
    }
}