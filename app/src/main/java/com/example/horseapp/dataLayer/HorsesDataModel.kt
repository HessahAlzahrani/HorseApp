package com.example.horseapp.dataLayer


data class HorsesDataModel(
    val id :Int,
    val itemName :String,
    val sorting : String,
    val Type:String, val image:String
)





object data {

    val resultItemHours: List <HorsesDataModel> = listOf(
         // item 1
        HorsesDataModel(121, "Alblack", "Hours", "Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg"),
         // item2
                HorsesDataModel(222,"mrwan","horse","English","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg"),
         //item3
        HorsesDataModel(223,"alshagab","mohrah","Arabian","https://i.ibb.co/5YWgL1Y/horse-herd-fog-nature-52500.jpg")

    )

}