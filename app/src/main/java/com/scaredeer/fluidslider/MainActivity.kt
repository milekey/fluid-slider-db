package com.scaredeer.fluidslider

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.scaredeer.fluidslider.databinding.MainActivityBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        val binding =
            DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
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