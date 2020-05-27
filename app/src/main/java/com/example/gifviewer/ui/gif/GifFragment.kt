package com.example.gifviewer.ui.gif

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

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
            etSearchbar.setOnEditorActionListener { _, id, _ ->
                if (id == EditorInfo.IME_ACTION_DONE) {
                    val keyword = etSearchbar.text.toString()
                    gifFragmentViewModel.updateSearchKeywordAndReset(keyword)
                    gifFragmentViewModel.searchGifs()
                }
                false
            }

            rvGifs.apply {
                adapter = gifAdapter

                addOnScrollListener(object: RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)

                        (rvGifs.layoutManager as? GridLayoutManager)?.let {
                            if (it.findLastCompletelyVisibleItemPosition() == it.itemCount - 1
                                && dy > 0) {
                                gifFragmentViewModel.loadMoreGifs()
                            }
                        }
                    }
                })
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
