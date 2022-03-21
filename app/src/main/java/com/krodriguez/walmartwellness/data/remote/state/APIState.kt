package com.krodriguez.walmartwellness.data.remote.state

import com.krodriguez.walmartwellness.data.remote.model.RemoteCountryItem

sealed class APIState {
    object Loading: APIState()
    data class Success(val data: List<RemoteCountryItem>): APIState()
    data class Empty(val error: String): APIState()
    data class Error(val error: String): APIState()
}