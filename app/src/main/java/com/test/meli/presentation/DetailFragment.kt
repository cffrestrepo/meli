package com.test.meli.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.test.meli.R
import com.test.meli.commons.Constants
import com.test.meli.commons.Constants.Companion.PRODUCT
import com.test.meli.databinding.FragmentDetailBinding
import com.test.meli.repository.models.ResultsModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : Fragment() {

    @Inject
    lateinit var picasso: Picasso
    private lateinit var binding: FragmentDetailBinding
    private var product: ResultsModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        product = arguments?.getParcelable(PRODUCT)
        initViews()
    }

    private fun initViews() {
        product?.let {
            with(binding) {

                if (it.title.isEmpty()) {
                    textTitle.visibility = View.GONE
                    textTitleDes.visibility = View.GONE
                } else {
                    textTitleDes.text = it.title
                }

                if (it.nickname.isEmpty()) {
                    textTitleNickName.visibility = View.GONE
                    textTitleNickNameDes.visibility = View.GONE
                } else {
                    textTitleNickNameDes.text = it.nickname
                }

                if (it.city_name.isEmpty()) {
                    textTitleCity.visibility = View.GONE
                    textTitleCityDes.visibility = View.GONE
                } else {
                    textTitleCityDes.text = it.city_name
                }

                if (it.address.isEmpty()) {
                    textTitleAddress.visibility = View.GONE
                    textTitleAddressDes.visibility = View.GONE
                } else {
                    textTitleAddressDes.text = it.address
                }

                if (it.price.isEmpty()) {
                    textTitlePrice.visibility = View.GONE
                    textTitlePriceDes.visibility = View.GONE
                } else {
                    textTitlePriceDes.text = it.price
                }

                loadImage()
            }
        }
    }

    private fun loadImage() {
        product?.let {
            val urlThumbnail = it.thumbnail.replace(Constants.HTTP, Constants.HTTPS)
            picasso.load(urlThumbnail)
                .fit()
                .centerCrop()
                .placeholder(R.drawable.search_image)
                .error(R.drawable.broken_image).into(binding.imgIcon)
        }
    }
}
