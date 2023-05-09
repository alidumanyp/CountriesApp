package com.aliduman.countries.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.aliduman.countries.adapter.CountryAdapter
import com.aliduman.countries.databinding.FragmentFeedBinding
import com.aliduman.countries.viewmodel.FeedViewModel


class FeedFragment : Fragment() {
    private var binding: FragmentFeedBinding? = null
    private lateinit var viewModel : FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())//şimdilik içi boş veriyoruz.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFeedBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this)[FeedViewModel::class.java]
        viewModel.refreshData()

        binding?.recyclerCountryList?.layoutManager = LinearLayoutManager(context)
        binding?.recyclerCountryList?.adapter = countryAdapter

        binding?.swipeRefreshLayout?.setOnRefreshListener {
            binding?.recyclerCountryList?.visibility = View.GONE
            binding?.countryError?.visibility = View.GONE
            binding?.countryLoading?.visibility = View.VISIBLE

            viewModel.refreshFromAPI()

            binding?.swipeRefreshLayout?.isRefreshing = false

        }

        observeLiveData()


        /*
        binding!!.feedButton.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(13)
            Navigation.findNavController(it).navigate(action)
        }
         */
    }

    private fun observeLiveData() {
        viewModel.countries.observe(viewLifecycleOwner, Observer {
            it?.let {
                binding?.recyclerCountryList?.visibility = View.VISIBLE
                countryAdapter.updateCountryList(it)
            }
        })

        viewModel.countryError.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.countryError?.visibility = View.VISIBLE
                } else {
                    binding?.countryError?.visibility = View.GONE
                }
            }
        })

        viewModel.countryLoading.observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it) {
                    binding?.countryLoading?.visibility = View.VISIBLE
                    binding?.recyclerCountryList?.visibility = View.GONE
                    binding?.countryError?.visibility = View.GONE
                } else {
                    binding?.countryLoading?.visibility = View.GONE
                }
            }
        })


    }


}