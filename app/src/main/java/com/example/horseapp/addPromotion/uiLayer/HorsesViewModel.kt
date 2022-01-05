package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.dataLayer.HorsesDataSource
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HorsesViewModel: ViewModel(){

    //var = listlivedata:listdatamodel
var allItemfromdatasuorse = MutableLiveData<MutableList<HorsesDataModel>>()

    //initialize function inside the class
    init {
        getAllHorsesfun()
    }

    fun getAllHorsesfun(){
        allItemfromdatasuorse.value = HorsesDataSource.resultItemHours

    }


//fun db
    fun addHorsefun (horsesDataModel: HorsesDataModel){
        // Add database in fireStore
        val db = Firebase.firestore

        db.collection("Horses")
                // add and generate key =id
            .add(horsesDataModel)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }




    }


}