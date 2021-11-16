package com.jan.rappimovies.app.usecases.movie

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.app.general.IS_FIRST_REQUEST
import com.jan.rappimovies.app.general.LAST_VISIBLE
import com.jan.rappimovies.app.general.POPULAR_CRITERION
import com.jan.rappimovies.data.movie.MovieRepository
import com.jan.rappimovies.domain.movie.MOVIE_POPULAR_CRITERION
import com.jan.rappimovies.usescases.movie.CheckRequireNewPageUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class CheckRequireNewPageUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var movieRepository: MovieRepository

    private lateinit var checkRequireNewPageUseCase: CheckRequireNewPageUseCase


    @Before
    fun setup() {
        checkRequireNewPageUseCase = CheckRequireNewPageUseCase(movieRepository)
    }

    @Test
    fun `verify execute`() = runBlockingTest {

        val expectedResult = Unit

        `when`(checkRequireNewPageUseCase.invoke(LAST_VISIBLE, POPULAR_CRITERION, IS_FIRST_REQUEST))
            .thenReturn(expectedResult)

        val result =
            checkRequireNewPageUseCase.invoke(LAST_VISIBLE, POPULAR_CRITERION, IS_FIRST_REQUEST)
        Assert.assertEquals(expectedResult, result)
    }
}