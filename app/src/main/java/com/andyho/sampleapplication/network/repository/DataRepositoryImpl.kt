package com.andyho.sampleapplication.network.repository

import com.andyho.sampleapplication.model.Survey
import com.andyho.sampleapplication.network.RetrofitRequest
import io.reactivex.Single
import io.reactivex.schedulers.Schedulers

object DataRepositoryImpl :
    DataRepository {
    override fun getSurvey(page: Int, pageSize: Int): Single<List<Survey>> {
        return RetrofitRequest.getApiResource()
            .getSurveys(page, pageSize).subscribeOn(Schedulers.io())
    }
}