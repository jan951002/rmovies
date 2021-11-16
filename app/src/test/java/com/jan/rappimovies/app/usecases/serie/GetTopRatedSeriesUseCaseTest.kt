package com.jan.rappimovies.app.usecases.serie

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.app.general.sortedSerieListByTopRated
import com.jan.rappimovies.data.serie.SerieRepository
import com.jan.rappimovies.domain.serie.Serie
import com.jan.rappimovies.usescases.serie.GetTopRatedSeriesUseCase
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetTopRatedSeriesUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var serieRepository: SerieRepository

    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var getTopRatedSeriesUseCase: GetTopRatedSeriesUseCase


    @Before
    fun setup() {
        getTopRatedSeriesUseCase = GetTopRatedSeriesUseCase(serieRepository, dispatcher)
    }

    @Test
    fun `get top rate series empty result`() = runBlockingTest {
        val expectedResult = listOf<Serie>()

        given { getTopRatedSeriesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedSeriesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `get top rate series with data result`() = runBlockingTest {
        val expectedResult = sortedSerieListByTopRated

        given { getTopRatedSeriesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedSeriesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `get first serie sorted by top rated`() = runBlockingTest {
        val expectedResult = sortedSerieListByTopRated

        given { getTopRatedSeriesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedSeriesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult.first(), result.first())
        }
    }
}