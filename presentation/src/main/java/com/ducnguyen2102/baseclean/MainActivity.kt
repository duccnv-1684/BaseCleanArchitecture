package com.ducnguyen2102.baseclean

import androidx.activity.viewModels
import com.ducnguyen2102.baseclean.base.BaseActivity
import com.ducnguyen2102.baseclean.databinding.ActivityMainBinding
import com.ducnguyen2102.baseclean.util.extension.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {
    override val viewBinding: ActivityMainBinding by viewBinding(ActivityMainBinding::inflate)

    override val viewModel: MainViewModel by viewModels()
}