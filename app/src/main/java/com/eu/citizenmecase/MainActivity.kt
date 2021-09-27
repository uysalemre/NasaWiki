package com.eu.citizenmecase

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eu.citizenmecase.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val mainActivityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeView()
    }

    fun initializeView() = with(binding) {
        lifecycleOwner = this@MainActivity
        viewModel = mainActivityViewModel
    }

    fun updateLoadingVisibility(isVisible: Boolean) {
        mainActivityViewModel.updateLoadingVisibility(isVisible)
    }
}