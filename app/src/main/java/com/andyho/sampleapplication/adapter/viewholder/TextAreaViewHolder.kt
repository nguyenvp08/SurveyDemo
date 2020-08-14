package com.andyho.sampleapplication.adapter.viewholder

import com.andyho.sampleapplication.databinding.TextareaLayoutBinding
import com.andyho.sampleapplication.model.Question

class TextAreaViewHolder(val binding: TextareaLayoutBinding) : BaseViewHolder(binding.root) {
    override fun bind(question: Question) {
        binding.text.text = "Question ${question.displayOrder}: ${question.text}"
    }
}