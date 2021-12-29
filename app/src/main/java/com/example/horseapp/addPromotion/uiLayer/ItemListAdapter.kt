package com.example.horseapp.addPromotion.uiLayer

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.databinding.ItemBinding


class ItemListAdapter :ListAdapter<HorsesDataModel,ItemListAdapter.ResultsItemViewHolder> (DiffCallback) {

    class ResultsItemViewHolder(var binding : ItemBinding) :RecyclerView.ViewHolder(binding.) {
//
//        fun bind (views:HorsesDataModel) {
//            binding.itemOfhorses =
//            binding.executePendingBindings
//        }

        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultsItemViewHolder {
        return ResultsItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }




    override fun onBindViewHolder(holder: ResultsItemViewHolder, position: Int) {
        val listProject=getItem(position)
        holder.bind(listProject)
    }





    //code diffcallback for comparison between : oldItem & newItem ##
    companion object DiffCallback : DiffUtil.ItemCallback<HorsesDataModel>() {
        override fun areItemsTheSame(oldItem: HorsesDataModel, newItem: HorsesDataModel): Boolean {
            return oldItem.itemName == newItem.itemName
        }

        override fun areContentsTheSame(oldItem: HorsesDataModel, newItem: HorsesDataModel): Boolean {
            return oldItem.itemName == newItem.itemName
        }

    }

}



