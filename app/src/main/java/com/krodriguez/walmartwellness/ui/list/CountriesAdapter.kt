package com.krodriguez.walmartwellness.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.krodriguez.walmartwellness.data.remote.model.RemoteCountryItem
import com.krodriguez.walmartwellness.databinding.CountriesItemLayoutBinding
import javax.inject.Singleton

@Singleton
class CountriesAdapter constructor(
    private var dataSet: List<RemoteCountryItem>
) : RecyclerView.Adapter<CountriesAdapter.CountriesViewHolder>() {

    fun updateDataSet(newDataset: List<RemoteCountryItem>) {
        dataSet = newDataset
        notifyDataSetChanged()
    }

    class CountriesViewHolder(private val binding: CountriesItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(dataItem: RemoteCountryItem) {
            binding.tvName.text = dataItem.name
            binding.tvRegion.text = dataItem.region
            binding.tvCode.text = dataItem.code
            binding.tvCapital.text = dataItem.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountriesViewHolder {
        val itemBinding =
            CountriesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountriesViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: CountriesViewHolder, position: Int) {
        holder.onBind(dataSet[position])
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}