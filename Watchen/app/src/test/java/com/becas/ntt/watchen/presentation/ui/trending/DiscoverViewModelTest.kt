package com.becas.ntt.watchen.presentation.ui.trending


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.liveData
import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.Resultado
import com.becas.ntt.watchen.domain.repository.MovieRepository
import io.mockk.coVerify
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

@RunWith(MockitoJUnitRunner::class)
class DiscoverViewModelTest{
    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Mock
    private lateinit var mockRepo : MovieRepository

    @Before
    fun setup(){
        MockitoAnnotations.initMocks(this)
    }

@Test
suspend fun viewModel(){


}


}


