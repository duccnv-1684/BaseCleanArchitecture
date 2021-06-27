package com.ducnguyen2102.baseclean.ui.productdetail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ducnguyen2102.baseclean.R
import com.ducnguyen2102.baseclean.base.BaseFragment
import com.ducnguyen2102.baseclean.databinding.FragmentProductDetailBinding
import com.ducnguyen2102.baseclean.util.extension.loadImage
import com.ducnguyen2102.baseclean.util.extension.setSingleClickListener
import com.ducnguyen2102.baseclean.util.extension.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProductDetailFragment : BaseFragment<FragmentProductDetailBinding, ProductDetailViewModel>(R.layout.fragment_product_detail) {
    override val viewBinding: FragmentProductDetailBinding by viewBinding(FragmentProductDetailBinding::bind)

    override val viewModel: ProductDetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() = with(viewBinding) {
        pickColor.setSingleClickListener {
            showColorPickerDialog()
        }
        submit.setSingleClickListener {
            val newName = name.editText?.text?.toString() ?: ""
            val newSku = sku.editText?.text?.toString() ?: ""
            if (viewModel.isInfoChanged(newName, newSku)) {
                if (viewModel.isInfoValid(newName, newSku))
                    showConfirmUpdatePopup(newName, newSku)
            } else {
                findNavController().navigateUp()
            }
        }
    }

    private fun observeData() = with(viewModel) {
        image.observe(viewLifecycleOwner) {
            viewBinding.image.loadImage(it)
        }
        name.observe(viewLifecycleOwner) {
            viewBinding.name.editText?.setText(it)
        }
        errorDescription.observe(viewLifecycleOwner) {
            viewBinding.errorDescription.editText?.setText(it)
        }
        sku.observe(viewLifecycleOwner) {
            viewBinding.sku.editText?.setText(it)
        }
        color.observe(viewLifecycleOwner) {
            viewBinding.color.editText?.setText(it.name)
        }
        colors.observe(viewLifecycleOwner) {

        }
        nameState.observe(viewLifecycleOwner) {
            when (it) {
                is NameDataState.Required -> viewBinding.name.error = getString(R.string.error_name_required)
                is NameDataState.LengthLimit -> viewBinding.name.error = getString(R.string.error_name_limit)
                is NameDataState.Valid -> viewBinding.name.error = null
            }
        }
        skuState.observe(viewLifecycleOwner) {
            when (it) {
                is SkuDataState.Required -> viewBinding.sku.error = getString(R.string.error_sku_required)
                is SkuDataState.LengthLimit -> viewBinding.sku.error = getString(R.string.error_sku_limit)
                is SkuDataState.Valid -> viewBinding.sku.error = null
            }
        }
        updateProductTask.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(requireActivity(), R.string.msg_update_success, Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            } else {
                showToast(R.string.error_unknow)
            }
        }
    }

    private fun showColorPickerDialog() {
        val colors = viewModel.colors.value ?: return
        val selectedColor = viewModel.color.value ?: return
        val selectedIndex = colors.indexOf(selectedColor).takeIf { it >= 0 } ?: 0
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_select_color)
            .setSingleChoiceItems(colors.map { it.name }.toTypedArray(), selectedIndex) { dialog, which ->
                try {
                    viewModel.selectColor(colors[which])
                } catch (e: IndexOutOfBoundsException) {

                } finally {
                    dialog.dismiss()
                }
            }
            .show()
    }

    private fun showConfirmUpdatePopup(newName: String, newSku: String) {
        AlertDialog.Builder(requireContext())
            .setTitle(R.string.title_change_info)
            .setNegativeButton(android.R.string.cancel) { dialog, which ->
                dialog.dismiss()
            }
            .setPositiveButton(android.R.string.ok) { dialog, which ->
                viewModel.changeInfo(newName, newSku)
                dialog.dismiss()
            }
            .show()
    }
}

internal sealed class NameDataState {
    object Required : NameDataState()
    object LengthLimit : NameDataState()
    object Valid : NameDataState()
}

internal sealed class SkuDataState {
    object Required : SkuDataState()
    object LengthLimit : SkuDataState()
    object Valid : SkuDataState()
}