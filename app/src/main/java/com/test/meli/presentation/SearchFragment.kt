package com.test.meli.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.test.meli.R
import com.test.meli.data.network.HandledError
import com.test.meli.databinding.FragmentSearchBinding
import com.test.meli.presentation.events.SearchEvents
import com.test.meli.presentation.states.SearchScreenStates
import com.test.meli.presentation.viewmodel.SearchViewModel
import com.test.meli.repository.models.ResultsModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    lateinit var viewModel: SearchViewModel

    @Inject
    lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

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

    private fun handledError(handledError: HandledError) {
        val message = when (handledError) {
            is HandledError.BadRequest -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.InternalServerError -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.NetworkConnection -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.ResourceNotFound -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.UnAuthorized -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.UnExpected -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
            is HandledError.Unknown -> {
                // TODO send event to analytics, make flow decisions, retries, other navigation, etc.
                handledError.message + ": " + handledError.code
            }
        }

        materialAlertDialogBuilder
            .setTitle(requireContext().getString(R.string.ups_title))
            .setMessage(message)
            .setPositiveButton(requireContext().getString(R.string.accept)) { _, _ -> }
            .show()
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
