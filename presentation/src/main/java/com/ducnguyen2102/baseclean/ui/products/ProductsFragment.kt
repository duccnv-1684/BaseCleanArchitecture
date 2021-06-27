package com.ducnguyen2102.baseclean.ui.products

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ducnguyen2102.baseclean.R
import com.ducnguyen2102.baseclean.base.BaseFragment
import com.ducnguyen2102.baseclean.databinding.FragmentProductsBinding
import com.ducnguyen2102.baseclean.util.extension.viewBinding
import com.ducnguyen2102.baseclean.util.widget.EndlessRecyclerViewOnScrollListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProductsFragment : BaseFragment<FragmentProductsBinding, ProductsViewModel>(R.layout.fragment_products) {
    override val viewBinding: FragmentProductsBinding by viewBinding(FragmentProductsBinding::bind)

    override val viewModel: ProductsViewModel by viewModels()

    private var productAdapter: ProductAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        observeData()
    }

    private fun setupUI() {
        productAdapter = ProductAdapter {
            (it as? ProductViewItem.Data)?.let {
                findNavController().navigate(ProductsFragmentDirections.actionProductsFragmentToProductDetailFragment(it.data.id))
            }
        }

        viewBinding.products.apply {
            adapter = productAdapter
            addOnScrollListener(object : EndlessRecyclerViewOnScrollListener() {
                override fun onLoadMore() {
                    viewModel.loadMoreProducts()
                }
            })
        }
    }

    private fun observeData() = with(viewModel) {
        products.observe(viewLifecycleOwner) {
            lifecycleScope.launch(exceptionHandler) {
                productAdapter?.setData(it)
            }
        }
    }
}