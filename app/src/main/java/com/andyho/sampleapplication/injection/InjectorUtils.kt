package com.andyho.sampleapplication.injection

import com.andyho.sampleapplication.viewmodel.factory.AFragmentFactory
import com.andyho.sampleapplication.viewmodel.factory.SurveyAdapterFragmentFactory

object InjectorUtils {
    fun provideAViewModelFactory(): AFragmentFactory {
        return AFragmentFactory()
    }

    fun provideSurveryModelFactory(): SurveyAdapterFragmentFactory {
        return SurveyAdapterFragmentFactory()
    }
}