package com.example.horseapp.dataLayer


data class HorsesDataModel(
    val id: Int = 0,
    val Data_horse_Name:String,
    val Data_horse_Sorting: String,
    val Data_horse_Type:String,
    val Data_horse_image: String,
    val data_horse_Content:String

)


object HorsesDataSource {

    fun loadList():MutableList<HorsesDataModel>{return resultItemHours}

    fun addPromotionHorses (Promotion:HorsesDataModel){
        resultItemHours.add(Promotion)
    }//end fun add

    val resultItemHours:MutableList<HorsesDataModel> = mutableListOf(
         // item 1
        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
         // item2
                HorsesDataModel(222,"mrwan","horse","English","https://i.ibb.co/Lh8Bm29/504020.jpg","color what"),
        //item3
        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),


        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),


        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),

        HorsesDataModel(121, "nagm", "Hours", "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),




        )

}