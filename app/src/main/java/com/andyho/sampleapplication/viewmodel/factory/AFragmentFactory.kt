package com.andyho.sampleapplication.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyho.sampleapplication.network.repository.DataRepositoryImpl
import com.andyho.sampleapplication.viewmodel.MainViewModel

class AFragmentFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(DataRepositoryImpl) as T
    }
}