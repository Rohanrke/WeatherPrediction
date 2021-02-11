package com.rohan.weatherprediction.feature.home.search

import android.os.Bundle
import android.text.InputFilter
import com.rohan.weatherprediction.R
import com.rohan.weatherprediction.base.presentation.BaseFragment
import com.rohan.weatherprediction.databinding.FragmentSearchBinding
import com.rohan.weatherprediction.feature.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchCityFragment : BaseFragment<FragmentSearchBinding>(R.layout.fragment_search) {

    private val searchViewModel: SearchCityViewModel by viewModel()
    private val homeViewModel: HomeViewModel by sharedViewModel()

    lateinit var binding: FragmentSearchBinding

    override fun initComponents(savedInstanceState: Bundle?, binding: FragmentSearchBinding) {
        this.binding = binding
        this.binding.lifecycleOwner = this@SearchCityFragment
        homeViewModel.updateTitle(getString(R.string.title_search_city))
        setupEditFilter()
        setupListener()
    }

    private fun setupListener() {
        binding.btnSave.setOnClickListener {
            val country = binding.edtCountry.text.toString()
            val city = binding.edtCity.text.toString()

            searchViewModel.validate(city = city, country = country)
        }
    }

    override fun observeLiveEvents() {
        searchViewModel.savedCity.observe(this, {
            homeViewModel.updateCity(city = it)
            activity?.run {
                onBackPressed()
            }
        })

        searchViewModel.errorMessageRes.observe(this, {
            showToast(it)
        })
    }

    private fun setupEditFilter() {
        var letterFilter =
            InputFilter { source, start, end, _, _, _ ->
                var filtered = ""
                for (i in start until end) {
                    val character = source[i]
                    if (!Character.isWhitespace(character) && Character.isLetter(character)) {
                        filtered += character
                    }
                }
                filtered
            }

        binding.edtCity.filters = arrayOf(letterFilter)
        binding.edtCountry.filters = arrayOf(letterFilter)
    }

}