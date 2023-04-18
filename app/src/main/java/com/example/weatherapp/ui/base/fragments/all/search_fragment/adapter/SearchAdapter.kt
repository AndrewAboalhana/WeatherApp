package com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemSearchBinding
import com.example.weatherapp.model.data.WeatherData

class SearchAdapter(private var searchList: List<WeatherData>,private val listener: SearchListInteraction):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount() = searchList.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        try {
            if (searchList != null){
                val currentItem = searchList[position]
                holder.binding.apply {
                    textViewCityName.text = currentItem.location.name
                    textViewLocalTime.text = currentItem.location.localtime
                    textViewTemperatureDegree.text = currentItem.current.temp_c.toString()
                    textViewWeatherDescription.text = currentItem.current.condition.text
                    root.setOnClickListener { listener.onClickSearchItem(searchList[0]) }
                    val date = currentItem.location.localtime
                    when (date.substringAfter(" ").substringBefore(":").toInt()) {
                        in 6..11 -> {
                            imageViewDay.setImageResource(R.drawable.morning)
                            // morning - set background for morning
                        }
                        in 12..17 -> {
                            imageViewDay.setImageResource(R.drawable.evening)
                            // afternoon - set background for afternoon
                        }
                        in 18..23, in 0..5 -> {
                            imageViewDay.setImageResource(R.drawable.night)
                            // evening/night - set background for evening/night
                        }
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("test", "Error in onBindViewHolder(): ${e.message}")
        }
    }


    class SearchViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem){
        val binding = ItemSearchBinding.bind(itemView)
    }

    fun updateTasks(newTask: List<WeatherData>){
        val diffResult = DiffUtil.calculateDiff(SearchDiffUtil(searchList, newTask))
        searchList = newTask
        diffResult.dispatchUpdatesTo(this)
    }


}