package com.test.meli.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.test.meli.R
import com.test.meli.databinding.FragmentSearchBinding
import com.test.meli.presentation.events.SearchEvents
import com.test.meli.presentation.states.SearchScreenStates
import com.test.meli.presentation.viewmodel.SearchViewModel
import com.test.meli.repository.models.ResultsModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : FragmentBase() {

    private lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(this)[SearchViewModel::class.java]
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observer()
        initSearchView()
    }

    private fun observer() {
        viewModel.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is SearchScreenStates.DataLoadedState -> {
                    findNavController().navigate(R.id.action_SearchFragment_to_ProductsFragment)
                }
                is SearchScreenStates.HandledErrorState -> {
                    handledError(state.error)
                }
                is SearchScreenStates.LoadingState -> {
                    binding.progressIndicator.isVisible = state.isVisible
                }
                is SearchScreenStates.HistoryProductsLoadedState -> {
                    loadProductsPreviewSearch(state.data)
                }
            }
        }
    }

    private fun loadProductsPreviewSearch(data: List<ResultsModel>) {

    }

    private fun initSearchView() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    Snackbar.make(
                        binding.searchView,
                        requireContext().getString(R.string.empty_search),
                        Snackbar.LENGTH_LONG
                    ).show()
                } else {
                    // TODO text of example: "Motorola%20G6#json"
                    viewModel.postEvent(SearchEvents.SearchEvent(query))
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // TODO we can search the previous results
                return false
            }
        })
    }
}
