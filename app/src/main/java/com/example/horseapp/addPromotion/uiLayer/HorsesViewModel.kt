package com.example.horseapp.addPromotion.uiLayer

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseapp.dataLayer.HorsesDataModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class HorsesViewModel : ViewModel() {

    //var = listlivedata:listdatamodel
    var allItemfromdatasuorse = MutableLiveData<List<HorsesDataModel>>(listOf())


    ///2 this (init) run when creating the class
    init {
        getAllPromotionFromFirebaseForShow_FORUSINGINIT()
    }


    fun getAllPromotionFromFirebaseForShow_FORUSINGINIT(){
        viewModelScope.launch {
            getAllPromotionFromFirebaseForShow().collect{
                Log.e("TAG", "getAllPromotionFromFirebaseForShow_FORUSINGINITtttttttttttttttt: $it", )
                allItemfromdatasuorse.value = it
            }
        }
    }

    fun addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(horsesDataModel: HorsesDataModel) {
        viewModelScope.launch {
            addHorsefun(horsesDataModel)
        }
    }

    //fun add in db
    suspend fun addHorsefun(horsesDataModel: HorsesDataModel) {

        // call fun uploadImage() for run before fun addHorsefun() //###
        // this fun inside fun #####
        uploadImage(horsesDataModel).collect {


            // Add database in fireStore ######
            val db = Firebase.firestore

            val horse = hashMapOf(
                "firbase_horse_name" to horsesDataModel.Data_horse_Name,
                "firbase_horse_Content" to horsesDataModel.data_horse_Content,
                // it = imageList url back from firebase storage
                "firbase_horse_image" to it
            )

            db.collection("Horses")
                // add  = generate key =id
                .add(horse)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }


        }

    }

    // fun to upload image to fireStorage  : this fun with (async and await)
    fun uploadImage(horsesDataModel: HorsesDataModel): Flow<List<String>> = callbackFlow {

        val storageRef = Firebase.storage.reference
        val scope = async {
            val imageList = mutableListOf<String>()

            for (i in horsesDataModel.Data_horse_image) {
                val reference =
                    storageRef.child("images/${Calendar.getInstance().timeInMillis}")
                // val imageUri takes the Uri after uploading
                val imageUri = reference.putFile(i.toUri()).continueWithTask { task ->
                    reference.downloadUrl
                }.await()

                imageList.add(imageUri.toString())
            }

            return@async imageList
        }
        trySend(scope.await())

        awaitClose { }

    }




    //
   suspend fun getAllPromotionFromFirebaseForShow(): Flow<List<HorsesDataModel>> = callbackFlow{

        try {
            val db = Firebase.firestore
            db.collection("Horses")
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        return@addSnapshotListener
                    }

                    var list = mutableListOf<HorsesDataModel>()
                    snapshot?.documents?.forEach {
                        if (it.exists()) {
                            Log.e("TAG", "getAllPromotionFromFirebaseForShownnn: $it", )
                            val productList = it.toObject(HorsesDataModel::class.java)
                            list.add(productList!!)
                        }

                    }
                    trySend(list)

                }

            awaitClose {

            }


        } catch (exception: Exception) {
            Log.e("Exception", "getAllProducts: ${exception.message.toString()}")

        }

    }// end......
}


