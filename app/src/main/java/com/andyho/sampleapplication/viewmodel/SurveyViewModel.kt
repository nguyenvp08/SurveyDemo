package com.andyho.sampleapplication.viewmodel

import androidx.lifecycle.ViewModel
import com.andyho.sampleapplication.model.Survey
import io.reactivex.disposables.CompositeDisposable

class SurveyViewModel : ViewModel() {

    val compositeSubscription = CompositeDisposable()

    private var survey: Survey? = null

    fun setImage(survey: Survey) {
        this.survey = survey
    }


    override fun onCleared() {
        super.onCleared()
        compositeSubscription.dispose()
    }
}