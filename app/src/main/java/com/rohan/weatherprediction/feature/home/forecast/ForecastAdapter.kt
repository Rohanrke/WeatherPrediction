package com.rohan.weatherprediction.feature.home.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rohan.weatherprediction.databinding.ItemForecastBinding
import com.rohan.weatherprediction.domain.entity.ListItemEntity
import com.rohan.weatherprediction.R

class ForecastAdapter : ListAdapter<ListItemEntity, ForecastAdapter.ListItemViewHolder>(ListItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemForecastBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_forecast,
            parent,
            false
        )
        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    class ListItemDiffUtil : DiffUtil.ItemCallback<ListItemEntity>() {
        override fun areContentsTheSame(oldItem: ListItemEntity, newItem: ListItemEntity): Boolean {
            return oldItem == newItem
        }

        override fun areItemsTheSame(oldItem: ListItemEntity, newItem: ListItemEntity): Boolean {
            return oldItem == newItem
        }
    }

    class ListItemViewHolder(private val binding: ItemForecastBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(entity: ListItemEntity) {
            binding.item = entity
        }
    }
}

