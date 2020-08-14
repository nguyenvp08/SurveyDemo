package com.andyho.sampleapplication.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.andyho.sampleapplication.model.Question

abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    abstract fun bind(question : Question)
}