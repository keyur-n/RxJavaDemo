package com.example.rxdemo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rxdemo.databinding.AdapterHomeBinding
import com.jakewharton.rxbinding4.view.clicks

class HomeAdapter(val list: MutableList<HomeData>,val onClick:(Int)->Unit) :RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    inner class HomeViewHolder(val binding: AdapterHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setView(){
            binding.btHello.text=list[adapterPosition].name
            binding.btHello.setOnClickListener {
                onClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = AdapterHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.setView()
    }

    override fun getItemCount(): Int {
        return list.size
    }
}