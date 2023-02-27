package com.becas.ntt.watchen.presentation.ui.discover

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.os.Looper
import androidx.lifecycle.Observer
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.Videos
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.discover.LiveDataTestUtils.getOrAwaitValue
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.robolectric.RobolectricTestRunner
import org.robolectric.Shadows.shadowOf


@RunWith(RobolectricTestRunner::class)
class DiscoverViewModelTest{
    private lateinit var viewModel: DiscoverViewModel

    private val mockRepository = mock(MovieRepository::class.java)

    @Mock
    private lateinit var myObjectObserver: Observer<List<MovieDTO>>

    @Captor
    private lateinit var myObjectCaptor: ArgumentCaptor<List<MovieDTO>>



    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = DiscoverViewModel()
        viewModel.getDiscoverLiveData().observeForever(myObjectObserver)
    }

    @Test
    fun sum(){
        assertEquals(4, 2+2)
    }

    @Test
     fun `fetchDiscover success`() = runTest{

        val movieList = listOf(
            MovieDTO(true,
                "",
                listOf(1),
                1,
                "",
                "",
                "",
                "",
                1.0,
                "",
                "",
                "MovieTest",
                false,
                1.0,
                Videos(listOf()),
                1

            )
        )

        doAnswer { invocation ->
            val onResult = invocation.arguments[0] as (List<MovieDTO>) -> Unit
            onResult(movieList)
            null
        }.`when`(mockRepository).getDiscover(any(), any())

//        `when`(mockRepository.getDiscover(any(), any())).thenAnswer { invocation ->
//            val callback: (List<MovieDTO>) -> Unit = invocation.getArgument(0)
//            callback(movieList)
//        }

        viewModel.getDiscoverMovies()


        assertEquals(movieList, viewModel.movieList.getOrAwaitValue())

//        myObjectCaptor.run {
//            verify(myObjectObserver, times(1)).onChanged(capture())
//            assertEquals(movieList, value)
//        }

//        shadowOf(Looper.getMainLooper()).idle()

    }
}
