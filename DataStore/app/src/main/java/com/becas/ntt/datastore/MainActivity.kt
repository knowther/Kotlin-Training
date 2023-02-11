package com.becas.ntt.datastore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import androidx.lifecycle.lifecycleScope
import com.becas.ntt.datastore.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding

    private val _dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override fun onCreate(savedInstanceState: Bundle?) {
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)
        super.onCreate(savedInstanceState)

        _binding.btnSave.setOnClickListener{
            lifecycleScope.launch{
                val key = stringPreferencesKey(_binding.edtKey.text.toString())
                _dataStore.edit { settings ->
                    settings[key] = _binding.edtValue.text.toString()
                }
            }
        }

        _binding.btnRead.setOnClickListener {
            val key = stringPreferencesKey(_binding.edtKey.text.toString())

            val exampleCounterFlow: Flow<String?> = _dataStore.data
                .map {preferences ->
                    preferences[key] ?: null
                }

            lifecycleScope.launch {
                exampleCounterFlow.collect{
                    _binding.tvValue.text = it
                }
            }

        }
    }
}