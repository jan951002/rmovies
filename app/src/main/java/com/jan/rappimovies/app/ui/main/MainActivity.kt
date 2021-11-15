package com.jan.rappimovies.app.ui.main

import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ActivityMainBinding
import com.jan.rappimovies.baseui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun layoutRes() = R.layout.activity_main
}