package com.test.meli.presentation

import android.app.ActivityManager
import android.app.AlertDialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.test.meli.R
import com.test.meli.commons.PermissionManager
import com.test.meli.commons.PermissionType
import com.test.meli.data.network.HandledError
import com.test.meli.presentation.events.SharedEvents
import com.test.meli.presentation.states.SharedStates
import com.test.meli.presentation.viewmodel.SharedViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
open class BaseFragment : Fragment() {

    @Inject
    lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    lateinit var viewModelShared: SharedViewModel
    lateinit var permissionManager: PermissionManager

    open fun successPermission(message: String) {}
    open fun errorPermission(message: String) {}

    fun observerBase() {
        viewModelShared = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
        viewModelShared.screenState.observe(viewLifecycleOwner) { state ->
            when (state) {
                SharedStates.requestPermimssionAllState -> {
                    viewModelShared.postEvent(SharedEvents.grantedPermimssionCallSuccessEvent)
                    permissionManager
                        .request(PermissionType.MandatoryForFeatureOne)
                        .rationale("We require to demonstrate that we can request two permissions at once")
                        .checkPermission { granted ->
                            if (granted) {
                                successPermission("YES! Now I can access Storage and Location!")
                            } else {
                                errorPermission("Still missing at least one permission :(")
                            }
                        }
                }
                SharedStates.requestPermimssionCameraState -> {
                    viewModelShared.postEvent(SharedEvents.grantedPermimssionCallSuccessEvent)
                    permissionManager
                        .request(PermissionType.Camera)
                        .rationale("We need permission to see your beautiful face")
                        .checkPermission { granted ->
                            if (granted) {
                                successPermission("We can see your face :)")
                            } else {
                                errorPermission("We couldn't access the camera :(")
                            }
                        }
                }
                SharedStates.requestPermimssionLocationState -> {
                    viewModelShared.postEvent(SharedEvents.grantedPermimssionCallSuccessEvent)
                    permissionManager
                        .request(PermissionType.Location)
                        .rationale("We need permission to your position")
                        .checkPermission { granted ->
                            if (granted) {
                                successPermission("sabemos tu ubocacion)")
                            } else {
                                errorPermission("Wesaber tu ubicacion")
                            }
                        }
                }
                SharedStates.requestPermimssionStoreState -> {
                    viewModelShared.postEvent(SharedEvents.grantedPermimssionCallSuccessEvent)
                    permissionManager
                        .request(PermissionType.Storage)
                        .rationale("We need permission to your storage")
                        .checkPermission { granted ->
                            if (granted) {
                                successPermission("tenemos acceso al almacenamiento")
                            } else {
                                errorPermission("We no podemos almacenar")
                            }
                        }
                }
                SharedStates.requestCallSuccessState -> {
                    // TODO() Permission request created
                }
            }
        }
    }

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
