package com.andyho.sampleapplication.adapter.viewholder

import android.view.LayoutInflater
import com.andyho.sampleapplication.databinding.ChoiceItemLayoutBinding
import com.andyho.sampleapplication.databinding.NpsChoiceLayoutBinding
import com.andyho.sampleapplication.databinding.TextfieldItemLayoutBinding
import com.andyho.sampleapplication.model.Question

class NpsChoiceViewHolder(val binding: NpsChoiceLayoutBinding) : BaseViewHolder(binding.root) {

    override fun bind(question: Question) {
        binding.text.text = "Question ${question.displayOrder}: ${question.text}"
        binding.layoutChoices.removeAllViews()
        question.answers.forEach {answer ->
            val viewBinding = ChoiceItemLayoutBinding.inflate(
                LayoutInflater.from(binding.root.context), binding.layoutChoices, true)
            viewBinding.textChoice.text = answer.text
        }
    }
}