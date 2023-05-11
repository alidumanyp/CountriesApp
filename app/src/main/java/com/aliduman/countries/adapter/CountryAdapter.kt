package com.aliduman.countries.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.aliduman.countries.R
import com.aliduman.countries.databinding.ItemCountryRowBinding
import com.aliduman.countries.model.Country
import com.aliduman.countries.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryHolder>(),CountryClickListener{

    class CountryHolder(var view: ItemCountryRowBinding) : RecyclerView.ViewHolder(view.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryHolder {
        //val binding = ItemCountryRowBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryRowBinding>(inflater, R.layout.item_country_row,parent,false)
        return CountryHolder(view)
    }

    override fun getItemCount(): Int {
        return countryList.size
    }

    override fun onBindViewHolder(holder: CountryHolder, position: Int) {
        /*
        holder.binding.nameText.text = countryList[position].countryName
        holder.binding.regionText.text = countryList[position].countryRegion

        holder.binding.feedView.getImageWithGlide(countryList[position].imageUrl, placeholderProgressBar(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
         */

        holder.view.root.tag = holder.view // Binding icin tag
        holder.view.country = countryList[position]
        holder.view.listener = this
    }

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {

        val binding = v.tag as? ItemCountryRowBinding ?: return
        val uuid = binding.country?.uuid ?: return
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
        /*
        val uuid = ItemCountryRowBinding.bind(v).countryUuidText.text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(uuid)
        Navigation.findNavController(v).navigate(action)
         */
    }

}