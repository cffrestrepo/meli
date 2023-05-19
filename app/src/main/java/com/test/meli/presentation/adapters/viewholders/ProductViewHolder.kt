package com.test.meli.presentation.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.test.meli.databinding.HolderProductBinding
import com.test.meli.repository.models.ResultsModel

class ProductViewHolder(private val binding: HolderProductBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(data: ResultsModel) {
        with(binding) {
            textTile.text = data.title
        }
    }
}