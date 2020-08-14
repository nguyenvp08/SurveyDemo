package com.andyho.sampleapplication.network.repository

import com.andyho.sampleapplication.model.Survey
import io.reactivex.Single

interface DataRepository {
    fun getSurvey(page: Int, pageSize:Int) : Single<List<Survey>>
}
