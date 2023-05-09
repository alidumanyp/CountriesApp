package com.aliduman.countries.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aliduman.countries.databinding.ItemCountryRowBinding
import com.aliduman.countries.model.Country
import com.aliduman.countries.util.getImageWithGlide
import com.aliduman.countries.util.placeholderProgressBar
import com.aliduman.countries.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(){

    class CountryHolder(val binding: ItemCountryRowBinding) : RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        val binding = ItemCountryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CountryHolder(binding)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        holder.binding.nameText.text = countryList[position].countryName
        holder.binding.regionText.text = countryList[position].countryRegion
        holder.binding.feedView.getImageWithGlide(countryList[position].imageUrl, placeholderProgressBar(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }

    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}