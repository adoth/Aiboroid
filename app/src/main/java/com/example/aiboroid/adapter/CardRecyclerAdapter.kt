package com.example.aiboroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.aiboroid.R
import com.example.aiboroid.model.Device

open class CardRecyclerAdapter(private val devices: List<Device>) : RecyclerView.Adapter<CardRecyclerAdapter.DevicesViewHolder>() {

    class DevicesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nickname: TextView = view.findViewById(R.id.device_nickname)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DevicesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.device_item, parent, false)
        val holder = DevicesViewHolder(view)
        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition
            val device = devices[position]
            onDeviceClicked(device.deviceId)
        }
        return holder
    }

    override fun getItemCount(): Int {
        return devices.size
    }

    override fun onBindViewHolder(holder: DevicesViewHolder, position: Int) {
        val device = devices[position]
        holder.nickname.text = device.nickname
    }

    open fun onDeviceClicked(deviceId: String) {}
}