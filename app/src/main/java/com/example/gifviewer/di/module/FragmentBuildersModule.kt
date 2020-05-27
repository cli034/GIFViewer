package com.example.gifviewer.di.module

import com.example.gifviewer.ui.gif.GifDetailFragment
import com.example.gifviewer.ui.gif.GifFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun gifFragment(): GifFragment

    @ContributesAndroidInjector
    abstract fun gifDetailFragment(): GifDetailFragment
}