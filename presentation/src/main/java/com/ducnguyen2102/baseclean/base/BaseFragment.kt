package com.ducnguyen2102.baseclean.base

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.ducnguyen2102.baseclean.util.extension.showDialog
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseFragment<Binding : ViewBinding, ViewModel : BaseViewModel>(@LayoutRes layoutId: Int) : Fragment(layoutId) {
    abstract val viewBinding: Binding

    abstract val viewModel: ViewModel

    private var toast: Toast? = null

    protected val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        Log.e("ducnguyen", throwable.message.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeException()
    }

    private fun subscribeException() = with(viewModel) {
        toastMessage.observe(viewLifecycleOwner) {
            showToast(it)
        }
        alertException.observe(viewLifecycleOwner) { pair ->
            context?.showDialog(title = pair.first, message = pair.second, positiveMessage = getString(android.R.string.ok))
        }
    }

    override fun onStop() {
        super.onStop()
        toast?.cancel()
    }

    protected fun showToast(@StringRes messageId: Int) {
        showToast(getString(messageId))
    }

    protected fun showToast(message: String) {
        toast?.cancel()
        toast = Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
        toast?.show()
    }
}