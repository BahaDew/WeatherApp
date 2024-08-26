package uz.bahadew.weatherapp.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.bahadew.weatherapp.R
import uz.bahadew.weatherapp.data.model.WeatherUIData
import uz.bahadew.weatherapp.databinding.ItemWeatherDataBinding
import kotlin.math.roundToInt

class MainWeatherListAdapter :
    ListAdapter<WeatherUIData, MainWeatherListAdapter.WViewHolder>(WDiffUtil) {

    private var onClickItem: ((WeatherUIData) -> Unit)? = null

    inner class WViewHolder(private val binding: ItemWeatherDataBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnClickListener {
                onClickItem?.invoke(getItem(adapterPosition))
            }
        }

        @SuppressLint("SetTextI18n")
        fun bind(position: Int) {
            getItem(position).let { item ->
                binding.region.text = "${item.region}  •  ${item.country}"
                binding.weather.text = item.weatherMain
                val celsius = item.temp - 273.15
                binding.temp.text = "${celsius.roundToInt()}˚"
                val image = when (item.weatherMain) {
                    "Clouds" -> R.drawable.cleary
                    "Sun", "Hot", "Clear" -> R.drawable.sunny
                    "Snow" -> R.drawable.snowy
                    "Rain" -> R.drawable.runny
                    else -> R.drawable.cleary
                }
                binding.bgImage.setImageResource(image)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WViewHolder {
        return WViewHolder(
            ItemWeatherDataBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: WViewHolder, position: Int) {
        holder.bind(position)
    }

    object WDiffUtil : DiffUtil.ItemCallback<WeatherUIData>() {
        override fun areItemsTheSame(oldItem: WeatherUIData, newItem: WeatherUIData): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: WeatherUIData, newItem: WeatherUIData): Boolean {
            return oldItem == newItem
        }
    }

    fun setOnClickItem(onClickItem: (WeatherUIData) -> Unit) {
        this.onClickItem = onClickItem
    }
}