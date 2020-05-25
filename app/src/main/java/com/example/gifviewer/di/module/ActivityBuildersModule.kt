package com.example.gifviewer.di.module

import com.example.gifviewer.ui.activity.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity
}