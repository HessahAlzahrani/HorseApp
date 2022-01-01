package com.example.horseapp.dataLayer

import android.net.Uri


data class HorsesDataModel(
    val id :Int,
    val Data_horse_Name :String,
    val Data_horse_Sorting : String,
    val Data_horse_Type:String,
    val Data_horse_image: String,
    val data_horse_Content:String

)


object data {

    val resultItemHours: List <HorsesDataModel> = listOf(
         // item 1
        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),
         // item2
                HorsesDataModel(222,"mrwan","horse","English","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color what"),
        //item3
        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color brawn"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color brawn"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color brawn"),


        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color brawn"),


        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg","color black"),




        )

}