package com.jan.rappimovies.app.ui.criterion

import android.annotation.SuppressLint
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ItemCriterionViewBinding
import com.jan.rappimovies.app.util.getPrimaryColor

@SuppressLint("NotifyDataSetChanged")
class CriterionViewHolder(
    private val binding: ItemCriterionViewBinding,
    private val context: Context,
    private val criterionAdapter: CriterionAdapter
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(criterion: Criterion, position: Int) {
        configSelected(position)
        binding.criterion = criterion
        itemView.setOnClickListener {
            criterionAdapter.positionSelected = position
            criterionAdapter.listener.onItemClick(criterion)
            criterionAdapter.notifyDataSetChanged()
        }
    }

    private fun configSelected(position: Int) {
        if (criterionAdapter.positionSelected == position) {
            binding.criterionCard.setCardBackgroundColor(context.getPrimaryColor())
            binding.criterionNameText.setTextColor(ContextCompat.getColor(context, R.color.white))
        } else {
            binding.criterionCard.setCardBackgroundColor(
                ContextCompat.getColor(context, R.color.white)
            )
            binding.criterionNameText.setTextColor(
                ContextCompat.getColor(context, R.color.primary_text)
            )
        }
    }
}