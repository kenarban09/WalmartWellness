package com.krodriguez.walmartwellness.di

import com.krodriguez.walmartwellness.data.remote.RemoteCountriesRepositoryImpl
import com.krodriguez.walmartwellness.domain.GetCountriesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object PresentationModule {

    @Provides
    fun provideGetCountriesUseCase(
        remote: RemoteCountriesRepositoryImpl
    ): GetCountriesUseCase {
        return GetCountriesUseCase(remote)
    }
}