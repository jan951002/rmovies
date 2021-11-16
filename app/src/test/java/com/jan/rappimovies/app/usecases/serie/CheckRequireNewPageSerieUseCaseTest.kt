package com.jan.rappimovies.app.usecases.serie

import com.jan.rappimovies.app.MainCoroutineRule
import com.jan.rappimovies.app.general.IS_FIRST_REQUEST
import com.jan.rappimovies.app.general.LAST_VISIBLE
import com.jan.rappimovies.app.general.POPULAR_CRITERION
import com.jan.rappimovies.app.general.TOP_RATED_CRITERION
import com.jan.rappimovies.data.serie.SerieRepository
import com.jan.rappimovies.usescases.serie.CheckRequireNewPageSerieUseCase
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
class CheckRequireNewPageSerieUseCaseTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var serieRepository: SerieRepository

    private lateinit var checkRequireNewPageUseCase: CheckRequireNewPageSerieUseCase


    @Before
    fun setup() {
        checkRequireNewPageUseCase = CheckRequireNewPageSerieUseCase(serieRepository)
    }

    @Test
    fun `verify execute with popular series case`() = runBlockingTest {

        val expectedResult = Unit

        `when`(checkRequireNewPageUseCase.invoke(LAST_VISIBLE, POPULAR_CRITERION, IS_FIRST_REQUEST))
            .thenReturn(expectedResult)

        val result =
            checkRequireNewPageUseCase.invoke(LAST_VISIBLE, POPULAR_CRITERION, IS_FIRST_REQUEST)
        Assert.assertEquals(expectedResult, result)
    }

    @Test
    fun `verify execute with top rated series case`() = runBlockingTest {

        val expectedResult = Unit

        `when`(
            checkRequireNewPageUseCase.invoke(
                LAST_VISIBLE, TOP_RATED_CRITERION, IS_FIRST_REQUEST
            )
        ).thenReturn(expectedResult)

        val result =
            checkRequireNewPageUseCase.invoke(LAST_VISIBLE, TOP_RATED_CRITERION, IS_FIRST_REQUEST)
        Assert.assertEquals(expectedResult, result)
    }
}