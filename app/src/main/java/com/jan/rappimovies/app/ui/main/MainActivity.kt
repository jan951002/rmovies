package com.jan.rappimovies.app.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ActivityMainBinding
import com.jan.rappimovies.app.ui.search.SearchActivity
import com.jan.rappimovies.baseui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun layoutRes() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupAppBar()
    }

    private fun setupAppBar() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNav.setupWithNavController(navController)
        binding.mainSearchEdit.setOnClickListener {
            startActivity(Intent(this, SearchActivity::class.java))
        }
    }

    fun configSearch(isMainFragment: Boolean) {
        if (isMainFragment)
            binding.mainSearchEdit.visibility = View.VISIBLE
        else
            binding.mainSearchEdit.visibility = View.GONE
    }
}