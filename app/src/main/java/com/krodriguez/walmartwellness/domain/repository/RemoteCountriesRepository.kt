package com.krodriguez.walmartwellness.domain.repository

import com.krodriguez.walmartwellness.data.remote.state.APIState
import kotlinx.coroutines.flow.Flow

interface RemoteCountriesRepository {
    suspend fun getCountries(): Flow<APIState>
}