package com.andyho.sampleapplication.fragment.adapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.andyho.sampleapplication.activity.SurveyActivity
import com.andyho.sampleapplication.databinding.ImageFragmentLayoutBinding
import com.andyho.sampleapplication.model.Survey
import com.bumptech.glide.Glide

class SurveyAdapterFragment : Fragment() {

    private lateinit var binding: ImageFragmentLayoutBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ImageFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (arguments?.getSerializable(EXTRA_SURVEY) as Survey?)?.let { survey ->
            displaySurvey(survey)
        }
        binding.btnSurvey.setOnClickListener {
            startSurvey()
        }
    }

    private fun startSurvey() {
        val activity = activity
        if (activity != null && isAdded) {
            (arguments?.getSerializable(EXTRA_SURVEY) as Survey?)?.let { survey ->
                SurveyActivity.startActivity(survey, activity)
            }
        }
    }

    private fun displaySurvey(survey: Survey) {
        Glide.with(this).load("${survey.coverImageUrl}l")
            .into(binding.image)
        binding.title.text = survey.title
        binding.description.text = survey.description
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.image.setImageBitmap(null)
        binding.image.setImageResource(0)
    }

    companion object {
        private const val EXTRA_SURVEY = "survey"

        fun getInstance(survey: Survey) = SurveyAdapterFragment()
            .apply {
            arguments = Bundle().apply {
                putSerializable(EXTRA_SURVEY, survey)
            }
        }
    }
}