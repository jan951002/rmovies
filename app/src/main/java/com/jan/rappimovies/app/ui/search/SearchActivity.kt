package com.jan.rappimovies.app.ui.search

import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ActivitySearchBinding
import com.jan.rappimovies.baseui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : BaseActivity<ActivitySearchBinding>(ActivitySearchBinding::inflate) {

    override fun layoutRes() = R.layout.activity_search
}