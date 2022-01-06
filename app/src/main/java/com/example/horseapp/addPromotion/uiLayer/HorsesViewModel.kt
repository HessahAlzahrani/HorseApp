package com.example.horseapp.addPromotion.uiLayer

import android.net.Uri
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
import com.google.firebase.storage.ktx.storage
import java.util.*
import kotlin.collections.ArrayList

class HorsesViewModel: ViewModel(){

    //var = listlivedata:listdatamodel
var allItemfromdatasuorse = MutableLiveData<MutableList<HorsesDataModel>>()
     var imageList: ArrayList<Uri?>? = arrayListOf()


    //initialize function inside the class
    init {
        getAllHorsesfun()
    }

    fun getAllHorsesfun(){
        allItemfromdatasuorse.value = HorsesDataSource.resultItemHours

    }


//fun add in db
    fun addHorsefun (horsesDataModel: HorsesDataModel) {
    // Add database in fireStore
    val db = Firebase.firestore

    db.collection("Horses")
        // add  = generate key =id
        .add(horsesDataModel)
        .addOnSuccessListener { documentReference ->
            Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
        }
        .addOnFailureListener { e ->
            Log.w("TAG", "Error adding document", e)
        }




    }
//
//    // fun to upload image to fireStorage
//    fun uploadImage(){
//        val storageRef = Firebase.storage.reference
//        val scope = async {
//            val imageList = mutableListOf<String>()
//            for (i in imagesList) {
//                val reference = storageRef.child("images/${Calendar.getInstance().timeInMillis}")
//                val imageUri = reference.putFile(i.toUri()).continueWithTask { task ->
//                    if (!task.isSuccessful) {
//                        task.exception?.let {
//                            throw it
//                        }
//                    }
//                    reference.downloadUrl
//                }.await()
//                imageList.add(imageUri.toString())
//            }
//            return@async imageList
//        }
//        trySend(scope.await())
//
//        awaitClose { }
//
//    }


}