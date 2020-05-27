package com.example.gifviewer.ui.gif

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil

import com.example.gifviewer.R
import com.example.gifviewer.databinding.FragmentGifDetailBinding
import dagger.android.support.DaggerFragment

/**
 * A simple [Fragment] subclass.
 */
class GifDetailFragment : DaggerFragment() {

    private lateinit var binding: FragmentGifDetailBinding

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
        return binding.root
    }

}
