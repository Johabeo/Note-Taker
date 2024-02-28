package com.example.project0note_taker

import Car
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class CarListAdapter : ListAdapter<Car, CarListAdapter.CarViewHolder>(CarsComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        return CarViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        val current = getItem(position)
        holder.bind(current.car)
    }

    class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carItemView: TextView = itemView.findViewById(R.id.textView)

        fun bind(text: String?) {
            carItemView.text = text
        }

        companion object {
            fun create(parent: ViewGroup): CarViewHolder {
                val view: View = LayoutInflater.from(parent.context)
                    .inflate(R.layout.recyclerview_item, parent, false)
                return CarViewHolder(view)
            }
        }
    }

    class CarsComparator : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Car, newItem: Car): Boolean {
            return oldItem.car == newItem.car
        }
    }
}