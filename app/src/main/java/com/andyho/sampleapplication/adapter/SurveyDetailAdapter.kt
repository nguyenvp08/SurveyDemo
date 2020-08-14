package com.andyho.sampleapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andyho.sampleapplication.adapter.viewholder.*
import com.andyho.sampleapplication.databinding.*
import com.andyho.sampleapplication.model.Question

class SurveyDetailAdapter(val questions: List<Question>) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TYPE_STAR_MILEY -> RatingViewHolder(RatingStarLayoutBinding.inflate(inflater, parent, false))
            TYPE_TEXTAREA -> TextAreaViewHolder(TextareaLayoutBinding.inflate(inflater, parent, false))
            TYPE_TEXTFIELD -> TextFieldViewHolder(TextfieldLayoutBinding.inflate(inflater, parent, false))
            TYPE_NPS_CHOICE -> NpsChoiceViewHolder(NpsChoiceLayoutBinding.inflate(inflater, parent, false))
            else -> IntroOutroViewHolder(IntroOutroLayoutBinding.inflate(inflater, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (questions[position].displayType) {
            "star" -> TYPE_STAR_MILEY
            "miley" -> TYPE_STAR_MILEY
            "smiley" -> TYPE_STAR_MILEY
            "heart" -> TYPE_STAR_MILEY
            "textfield" -> TYPE_TEXTFIELD
            "textarea" -> TYPE_TEXTAREA
            "nps" -> TYPE_NPS_CHOICE
            "choice" -> TYPE_NPS_CHOICE
            "dropdown" -> TYPE_DROPDOWN
            else -> TYPE_INTRO_OUTRO
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(questions[position])
    }

    override fun getItemCount(): Int = questions.count()

    companion object {
        private const val TYPE_INTRO_OUTRO = 1
        private const val TYPE_STAR_MILEY = 2
        private const val TYPE_TEXTFIELD = 3
        private const val TYPE_TEXTAREA = 4
        private const val TYPE_NPS_CHOICE = 5
        private const val TYPE_DROPDOWN = 6
    }
}