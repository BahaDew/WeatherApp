package uz.bahadew.weatherapp.presentation.screen.info

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import android.icu.text.TimeZoneFormat
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.databinding.FragmentInfoBinding
import uz.bahadew.weatherapp.utils.bahaLogger
import java.time.format.DateTimeFormatter
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
            sunrise.text = millisecondToTime(item.sunrise)
            sunset.text = millisecondToTime(item.sunset)
        }
    }

    private fun initFlow() = binding.apply {

    }

    private fun millisecondToTime(time: Long): String {
        val parser = SimpleDateFormat("ss", Locale.getDefault())
        val timeFormatter = SimpleDateFormat("mm:ss", Locale.getDefault())
        return timeFormatter.format(parser.parse("${(time/1000).toInt()}"))
    }
}