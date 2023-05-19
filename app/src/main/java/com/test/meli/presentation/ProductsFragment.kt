package com.test.meli.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.test.meli.R
import com.test.meli.databinding.FragmentProductsBinding

class ProductsFragment : Fragment() {

    lateinit var binding: FragmentProductsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_ProductsFragment_to_SearchFragment)
        }

        binding.buttonDetail.setOnClickListener {
            findNavController().navigate(R.id.action_ProductsFragment_to_detailFragment)
        }
    }
}