package com.example.gifviewer.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.gifviewer.di.viewmodel.ViewModelFactory
import com.example.gifviewer.di.viewmodel.ViewModelKey
import com.example.gifviewer.ui.trending.TrendingFragmentViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(TrendingFragmentViewModel::class)
    internal abstract fun trendingFragmentViewModel(viewModel: TrendingFragmentViewModel): ViewModel
}