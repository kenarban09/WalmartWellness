package com.krodriguez.walmartwellness.data.remote.model

import com.krodriguez.walmartwellness.fixture.CountriesFixture
import junit.framework.TestCase.assertEquals
import org.junit.Test

class RemoteCountriesItemTest {
    private val cut = CountriesFixture.createRemoteCountriesResponse()

    @Test
    fun `given CountriesItem, when check the arguments, then assert default values`() {
        cut.map { country ->
            assertEquals(country.capital, "")
            assertEquals(country.code, "")
            assertEquals(country.name, "")
            assertEquals(country.region, "")
        }
    }
}