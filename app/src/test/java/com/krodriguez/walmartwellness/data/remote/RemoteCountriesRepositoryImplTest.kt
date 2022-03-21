package com.krodriguez.walmartwellness.data.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.krodriguez.walmartwellness.TestCoroutineRule
import com.krodriguez.walmartwellness.data.remote.state.APIState
import junit.framework.Assert.assertNotNull
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner.Silent::class)
class RemoteCountriesRepositoryImplTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockService: RemoteCountriesService

    private lateinit var cut: RemoteCountriesRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = RemoteCountriesRepositoryImpl(mockService)
    }

    @Test
    fun `given RemoteCountriesService when call getCountries then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val success = APIState.Success(data = listOf())
            val flowResult = flowOf(success)

            // when
            whenever(mockService.getCountries()).thenAnswer {
                flowResult
            }

            cut.getCountries()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given RemoteCountriesService when call getCountries then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val errorMessage = "Error"
            val error = APIState.Error(errorMessage)
            val flowResult = flowOf(error)

            // when
            whenever(mockService.getCountries()).thenAnswer {
                flowResult
            }
            cut.getCountries()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
                assertEquals(error.error, errorMessage)
            }
        }

    @Test
    fun `given RemoteCountriesService when call getCountries then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val emptyMessage = "Empty"
            val error = APIState.Empty(emptyMessage)
            val flowResult = flowOf(error)

            // when
            whenever(mockService.getCountries()).thenAnswer {
                flowResult
            }
            cut.getCountries()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
                assertEquals(error.error, emptyMessage)
            }
        }
}