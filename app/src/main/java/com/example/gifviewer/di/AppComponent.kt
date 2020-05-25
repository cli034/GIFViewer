package com.example.gifviewer.di

import com.example.gifviewer.GifApplication
import com.example.gifviewer.di.module.ActivityBuildersModule
import com.example.gifviewer.di.module.FragmentBuildersModule
import com.example.gifviewer.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    ActivityBuildersModule::class,
    FragmentBuildersModule::class,
    ViewModelModule::class
])
interface AppComponent : AndroidInjector<GifApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(gifApplication: GifApplication): Builder

        fun build(): AppComponent
    }
}