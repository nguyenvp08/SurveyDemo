package com.andyho.sampleapplication.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.andyho.sampleapplication.R
import com.andyho.sampleapplication.activity.SurveyActivity
import com.andyho.sampleapplication.adapter.SurveyDetailAdapter
import com.andyho.sampleapplication.databinding.SurveyDetailFragmentBinding
import com.andyho.sampleapplication.model.Survey

class SurveyDetailFragment : Fragment() {

    private lateinit var binding: SurveyDetailFragmentBinding
    private lateinit var adapter: SurveyDetailAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = SurveyDetailFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (arguments?.get(SurveyActivity.EXTRA_SURVEY) as Survey?)?.let {survey ->
            binding.title.text = survey.title
            binding.recyclerView.layoutManager = LinearLayoutManager(context)
            adapter = SurveyDetailAdapter(survey.questions)
            binding.recyclerView.adapter = adapter
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_survey, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        activity?.finish()
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getInstance(survey: Survey) = SurveyDetailFragment().apply {
            arguments = Bundle().apply { putSerializable(SurveyActivity.EXTRA_SURVEY, survey) }
        }
    }
}