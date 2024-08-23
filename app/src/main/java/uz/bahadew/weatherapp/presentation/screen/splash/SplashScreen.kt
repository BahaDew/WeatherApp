package uz.bahadew.weatherapp.presentation.screen.splash

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentSplashBinding

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : Fragment(R.layout.fragment_splash) {
    private val binding by viewBinding(FragmentSplashBinding::bind)
    private val viewModel: SplashViewModel by viewModels<SplashViewModelImpl>()
    private var isFirstOpen = true

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            delay(3000)
            toMainScreen()
        }
    }

    override fun onResume() {
        super.onResume()
        if (!isFirstOpen) {
            toMainScreen()
        }
        isFirstOpen = false
    }

    private fun toMainScreen() {
        viewModel.toMainScreen()
    }
}