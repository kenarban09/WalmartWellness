package com.krodriguez.walmartwellness.di

import com.krodriguez.walmartwellness.data.remote.model.RemoteCountryItem
import com.krodriguez.walmartwellness.ui.list.CountriesAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UIModule {

    @Singleton
    @Provides
    fun provideCountriesItems(): List<RemoteCountryItem> {
        return arrayListOf()
    }

    @Singleton
    @Provides
    fun provideCountriesAdapter(dataSet: List<RemoteCountryItem>): CountriesAdapter {
        return CountriesAdapter(dataSet)
    }
}