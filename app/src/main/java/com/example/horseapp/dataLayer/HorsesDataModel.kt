package com.example.horseapp.dataLayer


data class HorsesDataModel(
    val id_horses_in_modelHorses: Int = 0,
//    val id_user_in_dataModelHorses:Int =0,
    val Data_horse_Name:String,
    val Data_horse_image: String,
    val data_horse_Content:String,

)//end dataClass HorsesDataModel

object HorsesDataSource {

    fun loadList():MutableList<HorsesDataModel>{return resultItemHours}
    fun addPromotionHorses (Promotion:HorsesDataModel){
        resultItemHours.add(Promotion)
    }//end fun add
    val resultItemHours:MutableList<HorsesDataModel> = mutableListOf(
        HorsesDataModel(11551121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black", ),
        HorsesDataModel(111222,"mrwan","https://i.ibb.co/Lh8Bm29/504020.jpg","color what"),
        HorsesDataModel(1161223,"alshagab","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),
        HorsesDataModel(1110121, "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(11661121, "Arabian","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(11751121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(111223,"alshagab","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),
        HorsesDataModel(11331121, "nagm", "https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(1141223,"alshagab","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),
        HorsesDataModel(1131121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(11771223,"alshagab","https://i.ibb.co/Lh8Bm29/504020.jpg","color brawn"),
        HorsesDataModel(117551121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(11142121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
        HorsesDataModel(11122121, "nagm","https://i.ibb.co/Lh8Bm29/504020.jpg","color black"),
    )//end val resultItemHours



}


data class UserDataModel(
    val id_user_in_data_modelUser :Int=0,
    val data_User_Name: String,
    val data_User_image: String,
    val data_User_contact :String,
)//end UserDataModel dataClass


object UserDataSource {

    val resultItemUser:MutableList<UserDataModel> = mutableListOf(UserDataModel(0, "ibrahim", "https://i.ibb.co/Lh8Bm29/504020.jpg", "from Ridha"))

}

