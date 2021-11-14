package com.jan.rappimovies.app.usecases.movie

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.domain.general.Error
import com.jan.rappimovies.domain.general.Result
import com.jan.rappimovies.domain.movie.MOVIE_POPULAR_CRITERION
import com.jan.rappimovies.usescases.movie.CheckRequireNewPageUseCase
import com.nhaarman.mockitokotlin2.given
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CheckRequireNewPageUseCaseTest {

    companion object {
        private const val LAST_VISIBLE = 12
        private const val CRITERION = MOVIE_POPULAR_CRITERION
        private const val IS_FIRST_REQUEST = true
    }

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private val dispatcher: CoroutineDispatcher = Dispatchers.Unconfined
    private lateinit var checkRequireNewPageUseCase: CheckRequireNewPageUseCase


    @Before
    fun setup() {
        checkRequireNewPageUseCase = CheckRequireNewPageUseCase(movieRepository, dispatcher)
    }

    @Test
    fun `check require new page popular movies with success result`() = runBlockingTest {

        val expectedResult = Result.Success(Unit)

        given { checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST) }
            .willReturn(flowOf(expectedResult))

        checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST)
            .collect { result -> Assert.assertEquals(expectedResult, result) }
    }

    @Test
    fun `check require new page popular movies with network failure result`() = runBlockingTest {

        val expectedResult = Result.Failure(Error.NetworkError("has been occurred an error"))

        given { checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST) }
            .willReturn(flowOf(expectedResult))

        checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST)
            .collect { result -> Assert.assertEquals(expectedResult, result) }
    }

    @Test
    fun `check require new page popular movies with unknown failure result`() = runBlockingTest {

        val expectedResult =
            Result.Failure(Error.UnknownError(Throwable("has been occurred an error")))

        given { checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST) }
            .willReturn(flowOf(expectedResult))

        checkRequireNewPageUseCase.invoke(LAST_VISIBLE, CRITERION, IS_FIRST_REQUEST)
            .collect { result -> Assert.assertEquals(expectedResult, result) }
    }
}