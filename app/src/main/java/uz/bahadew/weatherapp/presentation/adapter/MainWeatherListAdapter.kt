package uz.bahadew.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.bahadew.weatherapp.data.model.WeatherUIData
import uz.bahadew.weatherapp.databinding.ItemWeatherDataBinding

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

        fun bind(position: Int) {
            getItem(position).let { item ->
                binding.region.text = item.region
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