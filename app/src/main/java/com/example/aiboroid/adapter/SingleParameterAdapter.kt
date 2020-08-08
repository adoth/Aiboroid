package com.example.aiboroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aiboroid.R
import com.example.aiboroid.model.SingleParameter


open class SingleParameterAdapter(private val singleParameters: List<SingleParameter>) : RecyclerView.Adapter<SingleParameterAdapter.SingleParameterViewHolder>() {

    class SingleParameterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val displayName: TextView = view.findViewById(R.id.display_name)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SingleParameterViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_api, parent, false)
        val holder = SingleParameterViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val singleParameter = singleParameters[position]
            onApiClicked(singleParameter.parameter)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return singleParameters.size
    }

    override fun onBindViewHolder(holder: SingleParameterViewHolder, position: Int) {
        val singleParameter = singleParameters[position]
        holder.displayName.text = singleParameter.displayName
    }

    open fun onApiClicked(parameter: String) {}
}