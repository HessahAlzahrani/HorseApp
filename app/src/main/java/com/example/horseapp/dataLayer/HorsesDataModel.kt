package com.example.horseapp.dataLayer


data class HorsesDataModel(
    val id :Int,
    val itemName :String,
    val sorting : String,
    val Type:String
)



object data {

    val resultItemHours: List <HorsesDataModel> = listOf(
         // item 1
        HorsesDataModel(121, "Alblack", "Hours", "Arabian"),
         // item2
                HorsesDataModel(222,"mrwan","horse","English",),
         //item3
        HorsesDataModel(223,"alshagab","mohrah","Arabian",)

    )

}