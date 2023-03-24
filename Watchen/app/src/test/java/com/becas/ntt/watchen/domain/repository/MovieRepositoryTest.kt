package com.becas.ntt.watchen.domain.repository

import com.becas.ntt.watchen.data.webclient.model.dto.MovieResponseDTO
import com.becas.ntt.watchen.data.webclient.network.MovieWebClient
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.junit.Assert.*
import org.junit.Test
import retrofit2.Call

class MovieRepositoryTest{
    @Test
    fun When_get_Upcoming(){
        val webClient = mockk<MovieWebClient>()

        coEvery {
           webClient.buscarUpcoming()
        }

        webClient.buscarUpcoming()

        coVerify {
            webClient.buscarUpcoming()
        }
    }
}