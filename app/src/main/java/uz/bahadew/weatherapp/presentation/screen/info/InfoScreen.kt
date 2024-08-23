package uz.bahadew.weatherapp.presentation.screen.info

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentInfoBinding

@AndroidEntryPoint
class InfoScreen : Fragment(R.layout.fragment_info) {
    private val binding by viewBinding(FragmentInfoBinding::bind)
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFlow()
    }

    private fun initView() = binding.apply {

    }

    private fun initFlow() = binding.apply {

    }
}