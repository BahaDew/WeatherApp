package uz.bahadew.weatherapp.presentation.screen.info

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.util.TimeZone
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentInfoBinding
import java.util.Date
import java.util.Locale
import kotlin.math.roundToInt

@AndroidEntryPoint
class InfoScreen : Fragment(R.layout.fragment_info) {
    private val binding by viewBinding(FragmentInfoBinding::bind)
    private val viewModel: InfoViewModel by viewModels<InfoViewModelImpl>()
    private val navArgs by navArgs<InfoScreenArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initFlow()
    }

    @SuppressLint("SetTextI18n")
    private fun initView() = binding.apply {
        val image = when (navArgs.weaterUIData.weatherMain) {
            "Clouds" -> R.drawable.light_2
            "Sun", "Hot", "Clear" -> R.drawable.sun_2
            "Snow" -> R.drawable.snow
            "Rain" -> R.drawable.rain
            else -> R.drawable.sun_2
        }
        weatherImage.setImageResource(image)
        btnBack.setOnClickListener {
            viewModel.onClickBack()
        }
        navArgs.weaterUIData.let { item ->
            weather.text = item.weatherMain
            region.text = item.region
            country.text = item.country
            val celsius = item.temp - 273.15
            temp.text = " ${celsius.roundToInt()}"
            humidity.text = "${item.humidity} %"
            wind.text = "${item.windSpeed} m/s"
            sunrise.text = secondToTime(item.sunrise, item.timezone)
            sunset.text = secondToTime(item.sunset, item.timezone)
            dataTime.text = secondToDate(item.dataTime, item.timezone)
        }
    }

    private fun initFlow() = binding.apply {

    }

    private fun secondToTime(time: Long, timeZone: Long): String {
        val sdf = SimpleDateFormat("HH:mm", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date(time * 1000 + timeZone * 1000))
    }

    private fun secondToDate(time: Long, timeZone: Long): String {
        val sdf = SimpleDateFormat("HH:mm • dd/MM/yyyy", Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Date(time * 1000 + timeZone * 1000))
    }
}