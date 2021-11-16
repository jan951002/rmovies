package com.jan.rappimovies.app.usecases.movie

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.app.general.sortedListByTopRated
import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.usescases.movie.GetTopRatedMoviesUseCase
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
class GetTopRatedMoviesUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase


    @Before
    fun setup() {
        getTopRatedMoviesUseCase = GetTopRatedMoviesUseCase(movieRepository, dispatcher)
    }

    @Test
    fun `get top rate movies empty result`() = runBlockingTest {
        val expectedResult = listOf<Movie>()

        given { getTopRatedMoviesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedMoviesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `get top rate movies with data result`() = runBlockingTest {
        val expectedResult = sortedListByTopRated

        given { getTopRatedMoviesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedMoviesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `get first movie sorted by top rated`() = runBlockingTest {
        val expectedResult = sortedListByTopRated

        given { getTopRatedMoviesUseCase.invoke(true) }.willReturn(flowOf(expectedResult))

        getTopRatedMoviesUseCase.invoke(true).collect { result ->
            assertEquals(expectedResult.first(), result.first())
        }
    }
}