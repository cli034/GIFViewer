package com.example.gifviewer.di.module

import com.example.gifviewer.domain.repository.GifRepo
import com.example.gifviewer.domain.repository.GifRepoImpl
import dagger.Binds
import dagger.Module

@Module
abstract class RepositoryModule {

    @Binds
    abstract fun provideGifRepo(impl: GifRepoImpl): GifRepo
}