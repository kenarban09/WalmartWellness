package com.krodriguez.walmartwellness.di

import com.krodriguez.walmartwellness.data.remote.RemoteCountriesRepositoryImpl
import com.krodriguez.walmartwellness.data.remote.RemoteCountriesService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DomainModule {

    @Singleton
    @Provides
    fun provideRemoteCountriesRepository(
        service: RemoteCountriesService
    ): RemoteCountriesRepositoryImpl {
        return RemoteCountriesRepositoryImpl(service)
    }
}