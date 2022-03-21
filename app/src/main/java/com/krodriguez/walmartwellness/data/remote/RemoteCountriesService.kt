package com.krodriguez.walmartwellness.data.remote

import com.krodriguez.walmartwellness.data.remote.model.RemoteCountryItem
import retrofit2.Response
import retrofit2.http.GET

interface RemoteCountriesService {
    @GET("countries.json")
    suspend fun getCountries(): Response<List<RemoteCountryItem>>
}