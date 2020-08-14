package com.andyho.sampleapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.andyho.sampleapplication.model.Survey
import com.andyho.sampleapplication.network.repository.DataRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {

    val data = MutableLiveData<ArrayList<Survey>>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val isEndedLiveData = MutableLiveData<Boolean>()

    private val compositeSubscription = CompositeDisposable()
    private var currentPage = 1
    private var isLoading = false

    init {
        loadingLiveData.value = true
        loadCurrentPage()
    }

    private fun loadCurrentPage() {
        if (isLoading || isEndedLiveData.value == true) {
            return
        }

        compositeSubscription.add(dataRepository.getSurvey(currentPage, PAGE_SIZE)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading = true }
            .doOnSuccess { isLoading = false }
            .subscribe({surveys ->
            surveys?.let {
                loadingLiveData.value = false
                handleNewSurveys(it)
            }
        }, {
                isLoading = false
                isEndedLiveData.value = true
        }))
    }

    private fun handleNewSurveys(surveys: List<Survey>) {
        (if (currentPage == 1) ArrayList() else data.value)?.apply {
            addAll(surveys)
            data.value = this
        }
        isEndedLiveData.value = surveys.size <= 0
        currentPage++
    }

    override fun onCleared() {
        super.onCleared()
        compositeSubscription.dispose()
    }

    fun onPageTo(position: Int) {
        // check whether need to load other page
        if (position >=  ((data.value?.size ?: 0) - 2)) {
            loadCurrentPage()
        }
    }

    fun refresh() {
        // refresh values
        currentPage = 1
        isLoading = false
        isEndedLiveData.value = false
        loadingLiveData.value = true
        loadCurrentPage()
    }

    companion object {
        private const val PAGE_SIZE = 10
    }
}