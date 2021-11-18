package com.jan.rappimovies.app.ui.search.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ItemSerieSearchViewBinding
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.imagemanager.loadUrlCircle

class SerieSearchViewHolder (
    private val binding: ItemSerieSearchViewBinding,
    private val context: Context,
    private val listener: OnSearchClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(serie: Serie) {
        binding.serie = serie
        binding.serieImage.loadUrlCircle(
            "${context.getString(R.string.movies_db_base_url_img)}${serie.posterPath}",
            R.drawable.ic_place_holder
        )
        itemView.setOnClickListener { listener.onSerieItemClick(serie) }
    }
}