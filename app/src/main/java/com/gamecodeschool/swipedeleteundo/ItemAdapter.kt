package com.gamecodeschool.swipedeleteundo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.*
import com.gamecodeschool.swipedeleteundo.ItemAdapter.*
import com.gamecodeschool.swipedeleteundo.databinding.ItemLayoutBinding


class ItemAdapter: Adapter<ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemLayoutBinding): ViewHolder(binding.root)

    private val diffCallback = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
           return true
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(ItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = differ.currentList[position]

        holder.binding.apply{
            textView.text = "$item $position"
        }

    }

    override fun getItemCount()= differ.currentList.size

}