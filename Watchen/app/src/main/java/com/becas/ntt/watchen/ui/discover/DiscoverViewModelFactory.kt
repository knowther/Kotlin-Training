package com.becas.ntt.watchen.ui.discover

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.repository.MovieRepository

class DiscoverViewModelFactory constructor(private val repository: MovieRepository) : ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(DiscoverViewModel::class.java)) {
            DiscoverViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}