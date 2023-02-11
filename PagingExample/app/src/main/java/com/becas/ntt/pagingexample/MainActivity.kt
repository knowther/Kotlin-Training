package com.becas.ntt.pagingexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.becas.ntt.pagingexample.databinding.ActivityMainBinding
import com.becas.ntt.pagingexample.paging.LoaderAdapter
import com.becas.ntt.pagingexample.paging.QuotePagingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var recyclerView: RecyclerView
    lateinit var adapter : QuotePagingAdapter
    lateinit var quoteViewModel: QuoteViewModel
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.quoteList
        adapter = QuotePagingAdapter()
        quoteViewModel = ViewModelProvider(this).get(QuoteViewModel::class.java)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = adapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )

        quoteViewModel.list.observe(this, Observer {
            adapter.submitData(lifecycle, it)
        })

    }
}