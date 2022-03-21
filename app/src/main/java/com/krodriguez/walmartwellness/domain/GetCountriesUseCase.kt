package com.krodriguez.walmartwellness.domain

import com.krodriguez.walmartwellness.data.remote.RemoteCountriesRepositoryImpl
import com.krodriguez.walmartwellness.data.remote.state.APIState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

open class GetCountriesUseCase @Inject constructor(
    private val remoteRepository: RemoteCountriesRepositoryImpl
) {
    suspend fun execute(): Flow<APIState> = remoteRepository.getCountries()
}