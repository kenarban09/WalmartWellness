package com.krodriguez.walmartwellness.data.remote

import com.krodriguez.walmartwellness.data.remote.state.APIState
import com.krodriguez.walmartwellness.domain.repository.RemoteCountriesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class RemoteCountriesRepositoryImpl @Inject constructor(
    private val service: RemoteCountriesService
) : RemoteCountriesRepository {

    override suspend fun getCountries(): Flow<APIState> = flow { emit(service.getCountries()) }
        .onStart { APIState.Loading }
        .map { countries ->
            countries.body()?.sortedBy { it.code } ?: throw IllegalStateException("Error")
        }
        .map { countries -> APIState.Success(countries) }
        .flowOn(Dispatchers.IO)
        .conflate()
}
