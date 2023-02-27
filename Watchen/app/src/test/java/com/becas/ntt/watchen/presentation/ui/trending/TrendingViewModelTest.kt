//package com.becas.ntt.watchen.presentation.ui.trending
//
//import android.arch.core.executor.testing.InstantTaskExecutorRule
//import android.support.annotation.VisibleForTesting
//import androidx.lifecycle.LiveData
//import androidx.lifecycle.Observer
//import androidx.lifecycle.liveData
//import androidx.paging.PagingData
//import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
//import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
//import com.becas.ntt.watchen.data.webclient.model.dto.Videos
//import com.becas.ntt.watchen.data.webclient.network.Resultado
//import com.becas.ntt.watchen.domain.repository.MovieRepository
//import com.becas.ntt.watchen.presentation.ui.discover.DiscoverViewModel
//import org.hamcrest.CoreMatchers.not
//import org.hamcrest.CoreMatchers.nullValue
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.junit.runners.JUnit4
//import org.mockito.Mock
//import org.mockito.MockitoAnnotations
//import java.util.concurrent.CountDownLatch
//import java.util.concurrent.TimeUnit
//import java.util.concurrent.TimeoutException
//
//@RunWith(JUnit4::class)
//class TrendingViewModelTest{
//    @get:Rule
//    val rule = InstantTaskExecutorRule()
//
//    private lateinit var viewModel : DiscoverViewModel
//
//    @Mock
//    private lateinit var movieListObserver: Observer<List<MovieDTO>>
//
//    @Before
//    fun setup(){
//        MockitoAnnotations.initMocks(this)
//    }
//
//    @Test
//    suspend fun `when viewModel getTrending get sucess then set moviesLiveData`(){
//        val repo = MockRepo()
//
//
//    }
//
//}
//
//class MockRepo(): MovieRepository() {
//
//
//@VisibleForTesting(otherwise = VisibleForTesting.NONE)
//fun <T> LiveData<T>.getOrAwaitValue(
//    time: Long = 2,
//    timeUnit: TimeUnit = TimeUnit.SECONDS,
//    afterObserve: () -> Unit = {}
//): T {
//    var data: T? = null
//    val latch = CountDownLatch(1)
//    val observer = object : Observer<T> {
//        override fun onChanged(o: T?) {
//            data = o
//            latch.countDown()
//            this@getOrAwaitValue.removeObserver(this)
//        }
//    }
//    this.observeForever(observer)
//
//    try {
//        afterObserve.invoke()
//
//
//        if (!latch.await(time, timeUnit)) {
//            throw TimeoutException("LiveData value was never set.")
//        }
//
//    } finally {
//        this.removeObserver(observer)
//    }
//
//    @Suppress("UNCHECKED_CAST")
//    return data as T
//}
