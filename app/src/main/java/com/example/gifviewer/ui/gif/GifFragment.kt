package com.example.gifviewer.ui.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

import com.example.gifviewer.R
import com.example.gifviewer.databinding.FragmentGifBinding
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class GifFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var binding: FragmentGifBinding
    private lateinit var gifAdapter: GifAdapter
    private val gifFragmentViewModel: GifFragmentViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate<FragmentGifBinding>(
            inflater,
            R.layout.fragment_gif,
            container,
            false
        ).apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = gifFragmentViewModel
        }

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView()
        initViewModels()
    }

    private fun initView() {
        gifAdapter = GifAdapter {

        }

        with(binding) {
            rvGifs.apply {
                adapter = gifAdapter
            }
        }
    }

    private fun initViewModels() {
        with(gifFragmentViewModel) {
            gifObjectList.observe(viewLifecycleOwner, Observer {
                gifAdapter.submitList(it)
            })
        }
    }

}
