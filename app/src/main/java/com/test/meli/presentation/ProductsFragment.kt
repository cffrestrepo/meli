package com.test.meli.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import com.test.meli.databinding.FragmentProductsBinding
import com.test.meli.presentation.adapters.ProductsAdapter
import com.test.meli.presentation.events.ProductEvents
import com.test.meli.presentation.states.ProductStates
import com.test.meli.presentation.viewmodel.ProductsViewModel
import com.test.meli.repository.models.ResultsModel
import javax.inject.Inject

class ProductsFragment : BaseFragment() {

    @Inject
    lateinit var picasso: Picasso
    private lateinit var binding: FragmentProductsBinding
    lateinit var viewModel: ProductsViewModel
    private val productsAdapter: ProductsAdapter by lazy {
        ProductsAdapter(productSetOnClickListener = ::goToProductDetail, picasso = picasso)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[ProductsViewModel::class.java]
        binding = FragmentProductsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observer()
        initEvent()
    }

    private fun initEvent() {
        viewModel.postEvent(ProductEvents.initEvent)
    }

    private fun observer() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ProductStates.DataLoadedState -> {
                    populateAdapter(state.data)
                }
                is ProductStates.HandledErrorState -> {
                    handledError(state.error)
                }
                is ProductStates.LoadingState -> {
                    binding.progressIndicator.isVisible = state.isVisible
                }
            }
        }
    }

    private fun initViews() {
        with(binding.recyclerviewProducts) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = productsAdapter
        }
    }

    private fun populateAdapter(data: List<ResultsModel>) {
        productsAdapter.submitList(data)
    }

    private fun goToProductDetail(product: ResultsModel) {
        Toast.makeText(requireContext(), product.title, Toast.LENGTH_LONG).show()
    }
}
