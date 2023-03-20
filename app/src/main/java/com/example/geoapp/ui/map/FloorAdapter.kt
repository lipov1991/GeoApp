package com.example.geoapp.ui.map

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.geoapp.R
import com.example.geoapp.data.repository.Floor

class FloorAdapter(
    private var floors: List<Floor>

): RecyclerView.Adapter<FloorViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FloorViewHolder =
        FloorViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_floor, parent, false))

    override fun getItemCount(): Int = floors.size

    override fun onBindViewHolder(holder: FloorViewHolder, position: Int) {
        val floor = floors[position]

        holder.FloorNameTextView.text = floor.name
    }

    fun setData(floors: List<Floor>){
        this.floors = floors
        notifyDataSetChanged()

    }

}