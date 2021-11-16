package com.jan.rappimovies.app.ui.serie.list.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jan.rappimovies.adaptermanager.basicDiffUtil
import com.jan.rappimovies.adaptermanager.inflate
import com.jan.rappimovies.app.databinding.ItemSerieViewBinding
import com.jan.rappimovies.domain.serie.Serie

class SeriesAdapter(private val listener: OnSerieClickListener) :ListAdapter<Serie, SerieViewHolder>(
    basicDiffUtil<Serie>(
        areContentsTheSame = { oldItem, newItem -> oldItem.id == newItem.id },
        areItemsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val binding = parent.inflate(ItemSerieViewBinding::inflate)
        return SerieViewHolder(binding, parent.context, listener)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}