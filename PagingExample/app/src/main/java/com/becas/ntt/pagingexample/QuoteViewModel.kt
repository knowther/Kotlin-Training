package com.becas.ntt.pagingexample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.becas.ntt.pagingexample.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel @Inject constructor(val quoteRepository: QuoteRepository): ViewModel() {
    val list = quoteRepository.getQuotes().cachedIn(viewModelScope)
}