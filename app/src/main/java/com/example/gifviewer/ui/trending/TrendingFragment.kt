package com.example.gifviewer.ui.trending

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.gifviewer.R
import com.example.gifviewer.databinding.FragmentTrendingBinding

/**
 * A simple [Fragment] subclass.
 */
class TrendingFragment : Fragment() {

    lateinit var binding: FragmentTrendingBinding

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
