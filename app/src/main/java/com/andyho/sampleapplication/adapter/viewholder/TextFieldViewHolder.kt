package com.andyho.sampleapplication.adapter.viewholder

import android.view.LayoutInflater
import com.andyho.sampleapplication.databinding.TextfieldItemLayoutBinding
import com.andyho.sampleapplication.databinding.TextfieldLayoutBinding
import com.andyho.sampleapplication.model.Question

class TextFieldViewHolder(val binding: TextfieldLayoutBinding) : BaseViewHolder(binding.root) {

    override fun bind(question: Question) {
        binding.text.text = "Question ${question.displayOrder}: ${question.text}"
        binding.layoutTextFields.removeAllViews()
        question.answers.forEach {answer ->
            val viewBinding = TextfieldItemLayoutBinding.inflate(
                LayoutInflater.from(binding.root.context), binding.layoutTextFields, true)
            viewBinding.tvTitle.text = answer.text
        }
    }
}