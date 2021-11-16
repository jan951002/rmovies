package com.jan.rappimovies.app.ui.criterion

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.jan.rappimovies.adaptermanager.basicDiffUtil
import com.jan.rappimovies.adaptermanager.inflate
import com.jan.rappimovies.app.databinding.ItemCriterionViewBinding

class CriterionAdapter(
    val listener: OnCriterionClickListener
) : ListAdapter<Criterion, CriterionViewHolder>(
    basicDiffUtil<Criterion>(
        areContentsTheSame = { oldItem, newItem -> oldItem.name == newItem.name },
        areItemsTheSame = { oldItem, newItem -> oldItem == newItem }
    )
) {

    var positionSelected = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CriterionViewHolder {
        val binding = parent.inflate(ItemCriterionViewBinding::inflate)
        return CriterionViewHolder(binding, parent.context, this)
    }

    override fun onBindViewHolder(holder: CriterionViewHolder, position: Int) {
        holder.bind(getItem(position), position)
    }
}