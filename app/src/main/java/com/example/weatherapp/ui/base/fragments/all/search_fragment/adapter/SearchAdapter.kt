package com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.databinding.ItemSearchBinding
import com.example.weatherapp.model.data.WeatherData

class SearchAdapter(
    private var searchList: List<WeatherData>
):RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> ItemSearchBinding = ItemSearchBinding::inflate

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_search, parent,false)
        return SearchViewHolder(view)
    }

    override fun getItemCount() = searchList.size


    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
      val currentItem =searchList[position]
      holder.binding.apply {
        textViewCityName.text = currentItem.location.name
          textViewLocalTime.text = currentItem.location.localtime
          textViewTemperatureDegree.text = currentItem.current.temp_c.toString()
          textViewWeatherDescription.text = currentItem.current.condition.text
      }
    }

    fun updateTasks(newTask: List<WeatherData>){
        val diffResult = DiffUtil.calculateDiff(SearchDiffUtil(searchList, newTask))
        searchList = newTask
        diffResult.dispatchUpdatesTo(this)
    }

    class SearchViewHolder(viewItem: View):RecyclerView.ViewHolder(viewItem){
        val binding = ItemSearchBinding.bind(itemView)
    }

}