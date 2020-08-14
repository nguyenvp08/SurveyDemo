package com.andyho.sampleapplication.adapter.viewholder

import android.annotation.SuppressLint
import com.andyho.sampleapplication.databinding.RatingStarLayoutBinding
import com.andyho.sampleapplication.model.Question

class RatingViewHolder(val binding: RatingStarLayoutBinding) : BaseViewHolder(binding.root) {
    @SuppressLint("SetTextI18n")
    override fun bind(question: Question) {
        binding.text.text = "Question ${question.displayOrder}: ${question.text}"
        binding.rating.max = question.answers.count()
        binding.rating.stepSize = 1F
    }
}