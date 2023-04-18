package com.example.weatherapp.ui.base.fragments.all.search_fragment.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.weatherapp.model.data.WeatherData

class SearchDiffUtil(private val oldTasks: List<WeatherData>, private val newTasks: List<WeatherData>) :
    DiffUtil.Callback()
{
    override fun getOldListSize() = oldTasks.size

    override fun getNewListSize() = newTasks.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition].location== newTasks[newItemPosition].location

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int) =
        oldTasks[oldItemPosition] == newTasks[newItemPosition]
}