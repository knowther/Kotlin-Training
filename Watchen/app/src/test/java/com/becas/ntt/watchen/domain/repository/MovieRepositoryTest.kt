package com.becas.ntt.watchen.domain.repository

import com.becas.ntt.watchen.data.webclient.network.MovieWebClient
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.amshove.kluent.shouldBe
import org.junit.Assert.*
import org.junit.Test

class MovieRepositoryTest{
    @Test
    fun When_get_AuthToken(){
        val webClient = mockk<MovieWebClient>()
        val movieRepository = MovieRepository()
        coEvery {
           webClient.getAuthToken()
        }.returns(Unit)

        println(movieRepository.getAuthToken())

        coVerify {
            webClient.getAuthToken()
        }

    }
}