package com.example.gifviewer.di.module

import com.example.gifviewer.ui.trending.TrendingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun trendingFragment(): TrendingFragment
}