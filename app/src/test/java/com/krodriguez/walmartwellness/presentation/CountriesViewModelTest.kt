package com.krodriguez.walmartwellness.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.krodriguez.walmartwellness.TestCoroutineRule
import com.krodriguez.walmartwellness.data.remote.state.APIState
import com.krodriguez.walmartwellness.domain.GetCountriesUseCase
import com.krodriguez.walmartwellness.fixture.CountriesFixture
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CountriesViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    lateinit var getCountriesUseCaseMock: GetCountriesUseCase

    private lateinit var cut: CountriesViewModel

    @Mock
    lateinit var observer: Observer<APIState>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = CountriesViewModel(getCountriesUseCaseMock)
    }

    @Test
    fun `when call getCountries then returns success data`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = CountriesFixture.createRemoteCountriesResponse()
            val success = APIState.Success(result)

            // when
            cut.countriesLiveData.observeForever(observer)
            Mockito.`when`(getCountriesUseCaseMock.execute()).thenAnswer {
                flowOf(success)
            }
            cut.getCountries()

            // then
            assertNotNull(cut.countriesLiveData.value)
            assertEquals(success, cut.countriesLiveData.value)
        }

    @Test
    fun `when call getCountries then returns error`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Error("Error")

            // when
            cut.countriesLiveData.observeForever(observer)
            Mockito.`when`(getCountriesUseCaseMock.execute()).thenAnswer {
                flowOf(error)
            }
            cut.getCountries()

            // then
            assertNotNull(cut.countriesLiveData.value)
            assertEquals(error, cut.countriesLiveData.value)
        }

    @Test
    fun `when call getCountries then returns empty`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Empty("Error")

            // when
            cut.countriesLiveData.observeForever(observer)
            Mockito.`when`(getCountriesUseCaseMock.execute()).thenAnswer {
                flowOf(error)
            }
            cut.getCountries()

            // then
            assertNotNull(cut.countriesLiveData.value)
            assertEquals(error, cut.countriesLiveData.value)
        }
}