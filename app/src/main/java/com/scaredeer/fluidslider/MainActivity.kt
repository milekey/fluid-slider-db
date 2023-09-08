package com.scaredeer.fluidslider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.scaredeer.fluidslider.databinding.MainActivityBinding;

import kotlin.Unit;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        MainViewModel viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        MainActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.main_activity);
        // https://developer.android.com/topic/libraries/data-binding/architecture
        // https://developer.android.com/topic/libraries/data-binding/architecture#livedata
        // https://developer.android.com/topic/libraries/data-binding/architecture#viewmodel
        binding.setLifecycleOwner(this);
        binding.setViewModel(viewModel);

        binding.fluidSlider.setPositionListener(position -> {
            viewModel.setPosition(position);
            return Unit.INSTANCE;
        });
        binding.fluidSlider.setPosition(0.5f);
    }
}