package com.jan.rappimovies.app.ui.splash

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.lifecycle.lifecycleScope
import com.jan.rappimovies.app.R
import com.jan.rappimovies.app.databinding.ActivitySplashBinding
import com.jan.rappimovies.app.ui.main.MainActivity
import com.jan.rappimovies.baseui.BaseActivity
import kotlinx.coroutines.delay

class SplashActivity : BaseActivity<ActivitySplashBinding>(ActivitySplashBinding::inflate) {

    companion object {
        private const val SPLASH_TIME_OUT = 3000L
    }

    override fun layoutRes() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            val animation =
                AnimationUtils.loadAnimation(this@SplashActivity, R.anim.bottom_to_original)
            binding.splashImage.animation = animation
            delay(SPLASH_TIME_OUT)
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}