package com.example.gifviewer.ui.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide

import com.example.gifviewer.R
import com.example.gifviewer.databinding.FragmentGifDetailBinding
import dagger.android.support.DaggerFragment

class GifDetailFragment : DaggerFragment() {

    private lateinit var binding: FragmentGifDetailBinding

    private val safeArgs: GifDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate<FragmentGifDetailBinding>(
            inflater,
            R.layout.fragment_gif_detail,
            container,
            false
        )

        setupNavigation()

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()
    }

    private fun setupNavigation() {
        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.toolbar.setupWithNavController(navController, appBarConfiguration)
    }

    private fun initViews() {
        with(binding) {
            toolbar.setTitle(R.string.app_name)

            Glide.with(requireContext())
                .asGif()
                .load(safeArgs.gifUrl)
                .into(ivGif)
        }
    }

}
