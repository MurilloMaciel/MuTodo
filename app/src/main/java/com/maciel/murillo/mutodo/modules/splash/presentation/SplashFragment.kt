package com.maciel.murillo.mutodo.modules.splash.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.maciel.murillo.mutodo.R

private const val splashDelay = 2000L

class SplashFragment : Fragment() {

    private val startAppHandler = Handler(Looper.getMainLooper())

    private val startAppRunnable = Runnable {
        navController.navigate(SplashFragmentDirections.goToCategoriesFrag())
    }

    private val navController by lazy { findNavController() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onResume() {
        super.onResume()

        startAppHandler.postDelayed(startAppRunnable, splashDelay)
    }

    override fun onStop() {
        super.onStop()

        startAppHandler.removeCallbacks(startAppRunnable)
    }
}