package com.andyho.sampleapplication.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.andyho.sampleapplication.R
import com.andyho.sampleapplication.fragment.SurveyDetailFragment
import com.andyho.sampleapplication.model.Survey

class SurveyActivity : AppCompatActivity() {

    private var fragment: SurveyDetailFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.survery_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.title = ""

        if (supportFragmentManager.findFragmentByTag(SurveyDetailFragment::class.simpleName) == null) {
            SurveyDetailFragment.getInstance(getExtraSurvey()).also { aFragment ->
                fragment = aFragment
                supportFragmentManager.beginTransaction()
                    .add(R.id.container, aFragment, SurveyDetailFragment::class.simpleName)
                    .commit()
            }
        } else {
            supportFragmentManager.findFragmentByTag(SurveyDetailFragment::class.simpleName)?. let { it ->
                fragment = (it as SurveyDetailFragment?)?.also {
                    supportFragmentManager.beginTransaction()
                        .show(it)
                        .commit()
                }
            }
        }
    }

    fun getExtraSurvey(): Survey {
        return intent.getSerializableExtra(EXTRA_SURVEY) as Survey
    }

    companion object {
        const val EXTRA_SURVEY = "survey"

        fun startActivity(survey: Survey, context: Context) = Intent(context, SurveyActivity::class.java).apply {
            putExtra(EXTRA_SURVEY, survey)
            context.startActivity(this)
        }
    }
}