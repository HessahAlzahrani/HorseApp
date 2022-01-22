package com.example.horseapp.addPromotion.uiLayer

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.utilits.getUserId
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FirebaseFirestore
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
    var _horsesUsserlivedata = MutableLiveData<List<HorsesDataModel>>()
    var _userPromotion = MutableLiveData<List<HorsesDataModel>>()


    /***
    ///2 this (init) run when creating the class
     *  */
    init {
        getAllPromotionFromFirebaseForShow()
        getAllPromotionFromFirebaseForShowUser()
        // getUserpromotionbyuserID()
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
    fun addFunToCallCoroutineFunAddHorseFun_FORUSINGINIT(horsesDataModel: HorsesDataModel) {
        viewModelScope.launch {
            addHorsefun(horsesDataModel)
        }
    }

    /***
    //fun add in dataBase
    ///1//
     **/
    private suspend fun addHorsefun(horsesDataModel: HorsesDataModel) {
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
                "Data_horse_image" to it,
                "data_horse_seller" to getUserId()

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
    private fun uploadImage_TOfirebaseAND_return_LINK(horsesDataModel: HorsesDataModel): Flow<List<String>> =
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
        Log.e("hassah", "getAllPromotionFromFirebaseForShow: stasrt", )
        try {
            val db = Firebase.firestore
            /*** //name collection in fireStore **/
            db.collection("Horses")
                /*** // .addSnapshotListener() like liveData tListener for iny cheng *  */
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        return@addSnapshotListener
                    }

                    /*** //variable holder listDataModel in viewModel as object  **/
                    val horsesListholddataromfirbaseASDATAMODEL: MutableList<HorsesDataModel> = mutableListOf()
                    snapshot?.documents?.forEach {
                    Log.e("hassah", "getAllPromotionFromFirebaseForShow: ${it.data}", )
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


    fun getAllPromotionFromFirebaseForShowUser() {

        try {
            val db = Firebase.firestore
            /*** //name collection in fireStore **/
            db.collection("Horses").whereEqualTo("data_horse_seller", getUserId())//link horses by id user
                /*** // .addSnapshotListener() like liveData tListener for iny cheng *  */
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        return@addSnapshotListener
                    }
                    /*** //variable holder listDataModel in viewModel*  */
                    var horsesListholddataromfirbaseASDATAMODEL: MutableList<HorsesDataModel> = mutableListOf()
                    snapshot?.documents?.forEach {
                        if (it.exists()) {
                            val promotionListGetValueFromFirebaseAsDataModel =
                                it.toObject(HorsesDataModel::class.java)

                            horsesListholddataromfirbaseASDATAMODEL.add(
                                promotionListGetValueFromFirebaseAsDataModel!!
                            )
                        }
                    }
                    _horsesUsserlivedata.value = horsesListholddataromfirbaseASDATAMODEL
                }
        } catch (exception: Exception) {

        }
    }



            // function delete horses
//
//        fun deletePromotionHorsesFromListUser (){
//             val db = FirebaseFirestore.getInstance()
//              db.collection("Horses").whereEqualTo("data_horse_seller" , getUserId())
//         .delete()
//         .addOnSuccessListener {
//             Log.e("TAG","save:true")
//         }
//         .addOnFailureListener{e->
//             Log.e("TAG","save:error $e")
//         }
//




}// end.


