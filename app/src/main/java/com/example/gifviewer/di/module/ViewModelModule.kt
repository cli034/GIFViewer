package com.example.gifviewer.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifviewer.di.viewmodel.ViewModelFactory
import com.example.gifviewer.di.viewmodel.ViewModelKey
import com.example.gifviewer.ui.gif.GifFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(GifFragmentViewModel::class)
    internal abstract fun trendingFragmentViewModel(viewModel: GifFragmentViewModel): ViewModel
}