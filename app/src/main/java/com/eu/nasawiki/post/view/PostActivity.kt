package com.eu.nasawiki.post.view

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.eu.nasawiki.R
import com.eu.nasawiki.databinding.ActivityMainBinding
import com.eu.nasawiki.intro.AppIntroductionActivity
import com.eu.nasawiki.post.viewmodel.PostActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * @author Emre UYSAL
 * Main activity which is a container for fragments
 */
@AndroidEntryPoint
class PostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val postActivityViewModel: PostActivityViewModel by viewModels()

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (sharedPreferences.getBoolean("isFirstRun", true)) {
            startActivity(Intent(this, AppIntroductionActivity::class.java))
        }
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        initializeView()
    }

    private fun initializeView() = with(binding) {
        lifecycleOwner = this@PostActivity
        viewModel = postActivityViewModel
    }

    fun updateLoadingVisibility(isVisible: Boolean) {
        postActivityViewModel.updateLoadingVisibility(isVisible)
    }
}