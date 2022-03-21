package com.krodriguez.walmartwellness.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.krodriguez.walmartwellness.TestCoroutineRule
import com.krodriguez.walmartwellness.data.remote.RemoteCountriesRepositoryImpl
import com.krodriguez.walmartwellness.data.remote.state.APIState
import com.krodriguez.walmartwellness.fixture.CountriesFixture
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
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
class GetCountriesUseCaseTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    internal lateinit var mockRemoteRepository: RemoteCountriesRepositoryImpl

    private lateinit var cut: GetCountriesUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        cut = GetCountriesUseCase(mockRemoteRepository)
    }

    @Test
    fun `given isOnline true when execute then return Success API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val result = CountriesFixture.createRemoteCountriesResponse()
            val success = APIState.Success(result)
            val flowResult = flowOf(success)

            // when
            Mockito.`when`(mockRemoteRepository.getCountries()).thenAnswer {
                flowResult
            }
            cut.execute()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(success, state)
            }
        }

    @Test
    fun `given isOnline true when execute then return Error API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val error = APIState.Error("Error")
            val flowResult = flowOf(error)

            // when
            Mockito.`when`(mockRemoteRepository.getCountries()).thenAnswer {
                flowResult
            }
            cut.execute()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(error, state)
            }
        }

    @Test
    fun `given isOnline true when execute then return Empty API State`() =
        testCoroutineRule.runBlockingTest {
            // given
            val name = "Atlanta"
            val empty = APIState.Empty("Empty")
            val flowResult = flowOf(empty)

            // when
            Mockito.`when`(mockRemoteRepository.getCountries()).thenAnswer {
                flowResult
            }
            cut.execute()

            // then
            assertNotNull(flowResult)
            flowResult.collect { state ->
                assertEquals(empty, state)
            }
        }
}