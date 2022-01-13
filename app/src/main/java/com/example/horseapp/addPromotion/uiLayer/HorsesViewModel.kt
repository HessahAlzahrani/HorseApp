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
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class HorsesViewModel : ViewModel() {

    /***
    //var = listlivedata:listdatamod
     *  */
    var allItemfromdatasuorse = MutableLiveData<List<HorsesDataModel>>(listOf())

    var _horseslivedata = MutableLiveData<List<HorsesDataModel>>()

    /***
    ///2 this (init) run when creating the class
     *  */
    init {
        getAllPromotionFromFirebaseForShow()
//        getAllPromotionFromFirebaseForShow_FORUSINGINIT()
    }

//
//    fun getAllPromotionFromFirebaseForShow_FORUSINGINIT(){
//        viewModelScope.launch {
//            getAllPromotionFromFirebaseForShow().collect{
//                allItemfromdatasuorse.value = it
//            }
//        }
//    }
    fun addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(horsesDataModel: HorsesDataModel) {
        viewModelScope.launch {
            addHorsefun(horsesDataModel)
        }
    }
    /***
    //fun add in dataBase
    ///1//
     **/
   private suspend fun addHorsefun(horsesDataModel : HorsesDataModel) {
        /***
       // 3 // call fun uploadImage() after get link inside addHorsefun()   **/

        uploadImage_TOfirebaseAND_return_LINK(horsesDataModel).collect {

            /***
            // Add database in fireStore ######
             *  */
            val db = Firebase.firestore
            val horse = hashMapOf(   //hashMapOf -> key & valeo
                "Data_horse_Name" to horsesDataModel.Data_horse_Name,
                "data_horse_Content" to horsesDataModel.data_horse_Content,
                // it = imageList url back from firebase storage
                "Data_horse_image" to it
            )

            db.collection("Horses")
                /***
                // add  = generate key =id
                 *  */
                .add(horse)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
        }

    }

    /***
    // fun to upload image to fireStorage  : this fun with (async and await)
     await image for loading then make link for image and working fun
     *  */
   private fun uploadImage_TOfirebaseAND_return_LINK (horsesDataModel: HorsesDataModel): Flow<List<String>> =
        callbackFlow {
            val storageRef = Firebase.storage.reference
            val scope = async {
                val imageList = mutableListOf<String>()
                    //using (for loop ) because many images loading
                for (i in horsesDataModel.Data_horse_image) {
                    val reference =
                        storageRef.child("images/${Calendar.getInstance().timeInMillis}")
                    // val imageUri takes the Uri after uploading
                    val imageUri = reference.putFile(i.toUri()).continueWithTask { task ->
                        reference.downloadUrl
                    }.await() // await ( for )

                    imageList.add(imageUri.toString())
                }

                return@async imageList // async
            }
            trySend(scope.await()) // await val scope

            awaitClose { }

        }

    // 1- create function

    fun getAllPromotionFromFirebaseForShow() {

       try {
           val db = Firebase.firestore
           /*** //name collection in fireStore **/
           db.collection("Horses")
               /*** // .addSnapshotListener() like liveData tListener for iny cheng *  */
               .addSnapshotListener { snapshot, exception ->
                   if (exception != null) {
                       return@addSnapshotListener
                   }
                   /*** //variable holder listDataModel in viewModel*  */
                   var horsesListholddataromfirbaseASDATAMODEL = mutableListOf(HorsesDataModel())
                   snapshot?.documents?.forEach {
                       if (it.exists()) {
                           val promotionListGetValueFromFirebaseAsDataModel =
                               it.toObject(HorsesDataModel::class.java)

                           horsesListholddataromfirbaseASDATAMODEL.add(
                               promotionListGetValueFromFirebaseAsDataModel!!
                           )
                       }
                   }
                   _horseslivedata.value = horsesListholddataromfirbaseASDATAMODEL
               }
       } catch (exception: Exception) {

       }
   }

    }// end.

//    // 1 suspend  : make function for USED Carotene (sync & awet)
//   suspend fun getAllPromotionFromFirebaseForShow(): Flow<List<HorsesDataModel>> = callbackFlow{
//
//        try {
//            val db = Firebase.firestore
//            //name collection in fireStore
//            db.collection("Horses")
//                    // SnapshotListener like liveData tListener for iny cheng
//                .addSnapshotListener { snapshot, exception ->
//                    if (exception != null) {
//                        return@addSnapshotListener
//                    }
//                    //variable holder listDataModel
//                    var list = mutableListOf<HorsesDataModel>()
//                    snapshot?.documents?.forEach {
//                        if (it.exists()) {
//                            Log.e("TAG", "show me all the masege in app : $it", )
//                            val productList = it.toObject(HorsesDataModel::class.java)
//                            list.add(productList!!)
//                        }
//
//                    }
//                    trySend(list)
//
//                }
//
//            awaitClose {
//
//            }
//
//        } catch (exception: Exception) {
//            Log.e("Exception", "getAllProducts get all the promotion: ${exception.message.toString()}")
//
//        }
//
//    }// end......
//}


