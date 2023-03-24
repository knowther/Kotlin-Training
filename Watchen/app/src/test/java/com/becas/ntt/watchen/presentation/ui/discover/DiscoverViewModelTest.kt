package com.becas.ntt.watchen.presentation.ui.discover


import android.os.Looper
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.model.dto.Videos
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.discover.LiveDataTestUtils.getOrAwaitValue
import com.becas.ntt.watchen.presentation.ui.upcoming.UpcomingViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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


class DiscoverViewModelTest{
    private lateinit var viewModel: UpcomingViewModel

    private val mockRepository = mockk<MovieRepository>()
    private val onDataObserver = mockk<Observer<List<MovieDTO>?>>(relaxed = true)


    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        viewModel = UpcomingViewModel(mockRepository)
        viewModel.movieList.observeForever(onDataObserver)
    }

    @Test
     fun `when view model fetches data then it should call the repository`() = runTest{

          val movies = MovieResponseDTO(
              page = 1,
              results =  listOf(
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
              ),
              total_pages = 1,
              total_result = 1
          )



        val movieList = MutableLiveData<MovieResponseDTO?>()
        movieList.postValue(movies)
        every { mockRepository.getUpcoming() } returns movieList

        viewModel.getUpcomingMovies()

        verify{ mockRepository.getUpcoming()}
        verify { onDataObserver.onChanged(movieList.value?.results) }

    }
}
