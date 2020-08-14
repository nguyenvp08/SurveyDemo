package com.andyho.sampleapplication.viewmodel.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.andyho.sampleapplication.viewmodel.SurveyViewModel

class SurveyAdapterFragmentFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SurveyViewModel() as T
    }
}