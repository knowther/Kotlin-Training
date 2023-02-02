package com.becas.ntt.watchen.ui.trending

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.repository.MovieRepository

class TrendingViewModelFactory constructor(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(TrendingViewModel::class.java)) {
            TrendingViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}