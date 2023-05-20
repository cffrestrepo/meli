package com.test.meli.presentation

import androidx.fragment.app.Fragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.meli.R
import com.test.meli.data.network.HandledError
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    @Inject
    lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder

    protected fun handledError(handledError: HandledError) {
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
}