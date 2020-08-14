package com.andyho.sampleapplication.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andyho.sampleapplication.fragment.adapter.LoadingFragment
import com.andyho.sampleapplication.fragment.adapter.SurveyAdapterFragment
import com.andyho.sampleapplication.model.Survey


class SurveyAdapter constructor(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private val surveyData = arrayListOf<Survey>()

    var ended = false

    fun setDataList(list: List<Survey>?) {
        if (list != null) {
            surveyData.apply {
                clear()
                addAll(list)
                notifyDataSetChanged()
            }
        }
    }

    override fun getItemId(position: Int): Long {
        if (position < surveyData.count()) {
            return surveyData[position].id.hashCode().toLong()
        } else {
            return -1
        }
    }

    override fun containsItem(itemId: Long): Boolean {
        surveyData.forEach {
            if(it.id.hashCode().toLong() == itemId) {
                return true
            }
        }
        return super.containsItem(itemId)
    }

    override fun getItemCount(): Int = surveyData.count() + (if (!ended) 1 else 0)

    override fun createFragment(position: Int): Fragment {
        if (position < surveyData.size) {
            return SurveyAdapterFragment.getInstance(surveyData[position])
        } else {
            return LoadingFragment()
        }
    }
}