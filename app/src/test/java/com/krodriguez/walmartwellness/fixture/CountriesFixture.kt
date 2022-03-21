package com.krodriguez.walmartwellness.fixture

import com.krodriguez.walmartwellness.data.remote.model.RemoteCountryItem

object CountriesFixture {
    fun createRemoteCountriesResponse() = (0..5).map { RemoteCountryItem() }
}