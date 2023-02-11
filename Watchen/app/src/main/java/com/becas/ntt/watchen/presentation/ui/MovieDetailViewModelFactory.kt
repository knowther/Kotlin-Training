package com.becas.ntt.watchen.presentation.ui

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.becas.ntt.watchen.domain.repository.MovieRepository
import com.becas.ntt.watchen.presentation.ui.discover.DiscoverViewModel
import java.util.prefs.Preferences

class MovieDetailViewModelFactory constructor(private val repository: MovieRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MovieDetailViewModel::class.java)) {
            MovieDetailViewModel(this.repository) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}