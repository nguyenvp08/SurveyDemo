package com.andyho.sampleapplication.adapter.viewholder

import com.andyho.sampleapplication.databinding.IntroOutroLayoutBinding
import com.andyho.sampleapplication.model.Question

class IntroOutroViewHolder(val binding: IntroOutroLayoutBinding) : BaseViewHolder(binding.root) {
    override fun bind(question: Question) {
        binding.text.text = question.text
    }
}