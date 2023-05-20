package com.test.meli.presentation.adapters.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.test.meli.R
import com.test.meli.databinding.HolderProductBinding
import com.test.meli.repository.models.ResultsModel

class ProductViewHolder(private val binding: HolderProductBinding, private val picasso: Picasso) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        data: ResultsModel,
        productSetOnClickListener: (product: ResultsModel) -> Unit
    ) {
        initViews(data)
        initListener(data, productSetOnClickListener)
    }

    private fun initViews(data: ResultsModel) {
        with(binding) {
            textTile.text = data.title

            val urlThumbnail = data.thumbnail.replace("http://", "https://")

            picasso.load(urlThumbnail)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.search_image)
                .error(R.drawable.broken_image).into(imgIcon)
        }
    }

    private fun initListener(
        data: ResultsModel,
        productSetOnClickListener: (product: ResultsModel) -> Unit
    ) {
        binding.cardView.setOnClickListener {
            productSetOnClickListener(data)
        }
    }
}
