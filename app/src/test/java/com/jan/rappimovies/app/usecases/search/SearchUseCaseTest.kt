package com.jan.rappimovies.app.usecases.search

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.app.general.sortedListByTopRated
import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.data.search.SearchRepository
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.Movie
import com.jan.rappimovies.domain.search.Search
import com.jan.rappimovies.usescases.movie.GetTopRatedMoviesUseCase
import com.jan.rappimovies.usescases.search.SearchUseCase
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
class SearchUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var searchRepository: SearchRepository

    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var searchUseCase: SearchUseCase


    @Before
    fun setup() {
        searchUseCase = SearchUseCase(searchRepository, dispatcher)
    }

    @Test
    fun `search with a criterion that returns empty data`() = runBlockingTest {
        val expectedResult = Result.Success(listOf<Search>())

        given { searchUseCase.invoke("vxmcxnmcnmcxnmvcxncvxm", 1, true) }.willReturn(
            flowOf(expectedResult)
        )

        searchUseCase.invoke("vxmcxnmcnmcxnmvcxncvxm", 1, true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `search with a criterion that returns data`() = runBlockingTest {
        val fakeList: List<Search> = sortedListByTopRated.map { Search.MovieSearch(it) }
        val expectedResult = Result.Success(fakeList)

        given { searchUseCase.invoke("Batman", 1, true) }.willReturn(flowOf(expectedResult))

        searchUseCase.invoke("Batman", 1, true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `search with a criterion that returns unknown error`() = runBlockingTest {
        val expectedResult = Result.Failure(Error.UnknownError(Throwable("An error has ocurred")))

        given { searchUseCase.invoke("", 1, true) }.willReturn(flowOf(expectedResult))

        searchUseCase.invoke("", 1, true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }

    @Test
    fun `search with a criterion that returns network error`() = runBlockingTest {
        val expectedResult = Result.Failure(Error.NetworkError("An error has ocurred"))

        given { searchUseCase.invoke("", 1, false) }.willReturn(flowOf(expectedResult))

        searchUseCase.invoke("", 1, true).collect { result ->
            assertEquals(expectedResult, result)
        }
    }
}