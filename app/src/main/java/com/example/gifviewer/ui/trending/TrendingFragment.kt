package com.example.gifviewer.ui.trending

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider

import com.example.gifviewer.R
import com.example.gifviewer.databinding.FragmentTrendingBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class TrendingFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentTrendingBinding
    private val trendingFragmentViewModel: TrendingFragmentViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentTrendingBinding>(
            inflater,
            R.layout.fragment_trending,
            container,
            false
        ).apply {

        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModels()
    }

    private fun initView() {
        binding.layoutToolbar.toolbar.setTitle(R.string.app_name)
    }

    private fun initViewModels() {

    }

}
