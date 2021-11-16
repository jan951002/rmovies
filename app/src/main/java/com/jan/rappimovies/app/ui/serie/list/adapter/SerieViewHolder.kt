package com.jan.rappimovies.app.ui.serie.list.adapter

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ItemSerieViewBinding
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.imagemanager.loadUrl

class SerieViewHolder(
    private val binding: ItemSerieViewBinding,
    private val context: Context,
    private val listener: OnSerieClickListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(serie: Serie) {
        binding.serie = serie
        binding.serieImage.loadUrl(
            "${context.getString(R.string.movies_db_base_url_img)}${serie.posterPath}",
            R.drawable.ic_place_holder
        )
        itemView.setOnClickListener { listener.onItemClick(serie) }
    }
}