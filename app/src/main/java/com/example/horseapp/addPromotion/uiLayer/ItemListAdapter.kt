package com.example.horseapp.addPromotion.uiLayer

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.databinding.ItemBinding


class ItemListAdapter(var context: Context) :ListAdapter<HorsesDataModel,ItemListAdapter.ResultsItemViewHolder> (DiffCallback) {

    class ResultsItemViewHolder(var binding : ItemBinding) :RecyclerView.ViewHolder(binding.root) {

        /**
        link xmlItem with itemDataSource
         */
        fun bind(views: HorsesDataModel) {
            binding.itemNameId.text = views.Data_horse_Name

        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ResultsItemViewHolder {
        return ResultsItemViewHolder(
            ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ResultsItemViewHolder, position: Int) {

        /**
        // var listProject holds one object in the list of items
         */
        val listProject= getItem(position)
        holder.bind(listProject)

        /**
        // library for tek photos from link and holder for xml
         */
        if(listProject.Data_horse_image.size != 0) {
            Glide.with(context).load(listProject.Data_horse_image[0])
                .into(holder.binding.imageViewForShowinAdapterId)
        }else{
            Glide.with(context).load("https://firebasestorage.googleapis.com/v0/b/horse-app-4dfdc.appspot.com/o/images%2F1641669616481?alt=media&token=c259f9d1-d906-46f0-9ee2-ae14412ad568")
                .into(holder.binding.imageViewForShowinAdapterId)
        }

//
//        }

        /**
         *  // make Item like button
         */
        holder.binding.horsesItemCard.setOnClickListener{

            val actionForNafigatArgument =
                StartListFragmentDirections.actionStartListFragment2ToDetailsPromotionFragment(
                    itemNameArgument = listProject.Data_horse_Name,
                    imageUrlArgument = listProject.Data_horse_image.toTypedArray(),//toArray Images
                    itemContentArgument = listProject.data_horse_Content
                )
            holder.itemView.findNavController().navigate(actionForNafigatArgument)
        }

    }



    /**    //code diffcallback for comparison between : oldItem & newItem ##
    //comparison by the name
     */

    companion object DiffCallback : DiffUtil.ItemCallback<HorsesDataModel>() {
        override fun areItemsTheSame(oldItem: HorsesDataModel, newItem: HorsesDataModel): Boolean {
            return oldItem.Data_horse_Name == newItem.Data_horse_Name
        }

        override fun areContentsTheSame(oldItem: HorsesDataModel, newItem: HorsesDataModel): Boolean {
            return oldItem.Data_horse_Name == newItem.Data_horse_Name
        }

    }

}



