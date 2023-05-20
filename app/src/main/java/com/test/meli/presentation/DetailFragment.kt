package com.test.meli.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.test.meli.commons.Constants.Companion.PRODUCT
import com.test.meli.databinding.FragmentDetailBinding
import com.test.meli.repository.models.ResultsModel

class DetailFragment : Fragment() {

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
            binding.tvTitle.text = it.title
        }
    }
}
