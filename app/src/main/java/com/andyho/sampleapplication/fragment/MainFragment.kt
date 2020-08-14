package com.andyho.sampleapplication.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.andyho.sampleapplication.R
import com.andyho.sampleapplication.adapter.SurveyAdapter
import com.andyho.sampleapplication.databinding.AFragmentLayoutBinding
import com.andyho.sampleapplication.injection.InjectorUtils
import com.andyho.sampleapplication.model.Survey
import com.andyho.sampleapplication.viewmodel.MainViewModel

class MainFragment : Fragment() {
    private val viewModel : MainViewModel by viewModels {
        InjectorUtils.provideAViewModelFactory()
    }

    private lateinit var binding: AFragmentLayoutBinding
    private var adapter: SurveyAdapter? = null

    private val onPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.onPageTo(position)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = AFragmentLayoutBinding.inflate(inflater, container, false);
        return binding.root;
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.isEndedLiveData.observe(viewLifecycleOwner, Observer {
            adapter?.ended = (it == true)
        })

        viewModel.data.observe(viewLifecycleOwner, Observer {
            it?.let {
                displayData(it)
            }
        })
        viewModel.loadingLiveData.observe(viewLifecycleOwner, Observer {
            showLoading(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.viewPager.registerOnPageChangeCallback(onPageChangeCallback)
        setHasOptionsMenu(true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.viewPager.unregisterOnPageChangeCallback(onPageChangeCallback)
    }

    private fun showLoading(bool: Boolean?) {
        binding.progress.visibility = if (bool == true) View.VISIBLE else View.GONE
    }

    private fun displayData(data: ArrayList<Survey>) {
        activity?.let{act ->
            if (adapter == null) {
                adapter = SurveyAdapter(act)
                binding.viewPager.adapter = adapter
            }
            adapter?.setDataList(data)
            adapter?.notifyItemChanged(10)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.refresh) {
            viewModel.refresh()
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        fun getInstance() = MainFragment()
    }
}