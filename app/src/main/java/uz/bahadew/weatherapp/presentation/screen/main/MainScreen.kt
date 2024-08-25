package uz.bahadew.weatherapp.presentation.screen.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentMainBinding
import uz.bahadew.weatherapp.presentation.adapter.MainWeatherListAdapter

@AndroidEntryPoint
class MainScreen : Fragment(R.layout.fragment_main) {
    private val binding by viewBinding(FragmentMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()
    private val adapter = MainWeatherListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.requestAllRegionWeather()
    }

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
        srl.setOnRefreshListener {
            viewModel.requestAllRegionWeather()
        }
        adapter.setOnClickItem { item ->
            viewModel.onClickItem(item)
        }
    }

    private fun initFlow() = binding.apply {

        viewModel
            .allRegionWeather
            .onEach { list ->
                adapter.submitList(list)
            }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)

        viewModel
            .progressState
            .onEach { state ->
                srl.isRefreshing = state
            }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)

        viewModel
            .toastMessage
            .onEach { message ->
                Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT)
            }.flowWithLifecycle(lifecycle)
            .launchIn(lifecycleScope)
    }

}