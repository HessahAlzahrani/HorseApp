package com.example.horseapp.addPromotion.uiLayer

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.databinding.ItemBinding



class ItemListAdapter(var context: Context) :ListAdapter<HorsesDataModel,ItemListAdapter.ResultsItemViewHolder> (DiffCallback) {

    class ResultsItemViewHolder(var binding : ItemBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(views: HorsesDataModel) {
            binding.itemName.text = views.itemName
            binding.itemType.text= views.Type
//            binding.imageView3.setImageResource(views.image)



        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultsItemViewHolder {
        return ResultsItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context)))
    }




    override fun onBindViewHolder(holder: ResultsItemViewHolder, position: Int) {

        //var listProject holds one object in the list of items
        val listProject= getItem(position)
        holder.bind(listProject)


       // library for tek photo from link and holder for xml
        Glide.with(context).load(listProject.image).into(holder.binding.imageView3)



        // make Item like button
        holder.binding.itemListId.setOnClickListener{

        }

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



