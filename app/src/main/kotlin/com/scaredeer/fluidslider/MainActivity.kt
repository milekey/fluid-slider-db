package com.scaredeer.fluidslider

import android.os.Build
import android.os.Bundle
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.scaredeer.fluidslider.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val binding =
            DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)

        if (Build.VERSION_CODES.R <= Build.VERSION.SDK_INT) { // API-30+ (Android 11+)
            val insets = windowManager.currentWindowMetrics.windowInsets
                .getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemGestures())
            binding.root.updateLayoutParams<MarginLayoutParams> {
                leftMargin = insets.left
                rightMargin = insets.right
                bottomMargin = insets.bottom
            }
        }

        /*
        https://developer.android.com/topic/libraries/data-binding/architecture
        https://developer.android.com/topic/libraries/data-binding/architecture#livedata
        https://developer.android.com/topic/libraries/data-binding/architecture#viewmodel
         */
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.fluidSlider.positionListener = { position: Float? ->
            viewModel.setPosition(position!!)
        }
        binding.fluidSlider.position = 0.5f
    }
}