package com.aliduman.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.aliduman.countries.databinding.FragmentCountryBinding
import com.aliduman.countries.viewmodel.CountryViewModel

class CountryFragment : Fragment() {
    private var binding: FragmentCountryBinding? = null
    private var countryUuid = 0
    private lateinit var viewModel : CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {

        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[CountryViewModel::class.java]
        viewModel.getDataFromRoom()

        observeLiveData()


        /*
        arguments?.let {
            countryUuid = CountryFragmentArgs.fromBundle(it).countryUuid
        }
         */
    }

    private fun observeLiveData() {
        viewModel.countryLiveData.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding?.countryName?.text = it.countryName
                binding?.countryCapital?.text = it.countryCapital
                binding?.countryCurrency?.text = it.countryCurrency
                binding?.countryLanguage?.text = it.countryLanguage
                binding?.countryRegion?.text = it.countryRegion
            }
        })

    }

}