package com.krodriguez.walmartwellness.di

import android.content.Context
import com.krodriguez.walmartwellness.BuildConfig
import com.krodriguez.walmartwellness.CountriesApplication
import com.krodriguez.walmartwellness.data.remote.RemoteCountriesService
import com.krodriguez.walmartwellness.data.remote.interceptor.ConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): CountriesApplication {
        return app as CountriesApplication
    }

    @Provides
    @Singleton
    fun provideContext(application: CountriesApplication): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideMoshiBuilder(): MoshiConverterFactory {
        return MoshiConverterFactory.create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(moshi: MoshiConverterFactory, context: Context): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .addConverterFactory(moshi)
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(
                        HttpLoggingInterceptor().apply {
                            level = HttpLoggingInterceptor.Level.BODY
                        }
                    )
                    .addInterceptor(ConnectionInterceptor(context))
                    .build()
            )
    }

    @Singleton
    @Provides
    fun provideForecastService(retrofit: Retrofit.Builder): RemoteCountriesService {
        return retrofit
            .build()
            .create(RemoteCountriesService::class.java)
    }
}