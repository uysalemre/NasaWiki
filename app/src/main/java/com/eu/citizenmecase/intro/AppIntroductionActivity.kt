package com.eu.citizenmecase.intro

import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.eu.citizenmecase.R
import com.github.appintro.AppIntro
import com.github.appintro.AppIntroFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AppIntroductionActivity : AppIntro() {
    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences.edit().putBoolean("isFirstRun", false).apply()
        addSlide(
            AppIntroFragment.newInstance(
                title = "Welcome to " + getString(R.string.app_name),
                description = getString(R.string.welcome_intro_exp),
                backgroundDrawable = R.drawable.background_intro,
                imageDrawable = R.drawable.nasa_rocket_image
            )
        )
        addSlide(
            AppIntroFragment.newInstance(
                title = "Let's start NASA trip!",
                description = getString(R.string.welcome_intro_exp_second),
                backgroundDrawable = R.drawable.background_intro,
                imageDrawable = R.drawable.nasa_saturn_image
            )
        )
    }

    override fun onSkipPressed(currentFragment: Fragment?) {
        super.onSkipPressed(currentFragment)
        finish()
    }

    override fun onDonePressed(currentFragment: Fragment?) {
        super.onDonePressed(currentFragment)
        finish()
    }
}