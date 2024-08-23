package uz.bahadew.weatherapp.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentMainBinding
import uz.bahadew.weatherapp.presentation.adapter.MainWeatherListAdapter

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter = MainWeatherListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFlow()
    }

    private fun initView() = binding.apply {
        rvList.layoutManager = LinearLayoutManager(requireContext())
        rvList.adapter = adapter
        binding.btnSearch.setOnClickListener {

        }
    }

    private fun initFlow() = binding.apply {

    }

}