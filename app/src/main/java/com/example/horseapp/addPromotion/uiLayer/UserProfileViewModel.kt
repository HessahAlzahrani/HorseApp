package com.example.horseapp.addPromotion.uiLayer

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseapp.dataLayer.UserDataModel
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

class UserProfileViewModel : ViewModel() {

    var allItemfromdatasuorse = MutableLiveData<List<UserDataModel>>(listOf())

    val _Userlivedata = MutableLiveData<List<UserDataModel>>()

    //1 // craete db in fierbase
    private val databaseUserinfirebase = Firebase.firestore.collection(
        " UserDataModel"
    )

    init {
        getAllUsersFromFirebaseForShow()
    }

    fun addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(userDataModel: UserDataModel) {
        viewModelScope.launch {
            add_DB_USERfromFirebase(userDataModel)
        }

    }

    suspend fun add_DB_USERfromFirebase(userDataModel: UserDataModel) {

        uploadImage_TOFirebaseAND_return_LINK(userDataModel).collect {
            Log.e("TAG", "hessah00000000000000000000000000000000000000000): $it")

            // like datasuors in firebase (name in firebase = name in modeldata)
            val db_name_datasoursInFirebase = Firebase.firestore
            val User = hashMapOf(
                // image to it = (it) mane coll fun : uploadImage_TOFirebaseAND_return_LINK {
                "data_User_image" to it,
                "data_User_Name" to userDataModel.data_User_Name,
                "data_User_content" to userDataModel.data_User_content,
                "data_city_user" to userDataModel.data_city_user,
                "data_User_contact" to userDataModel.data_User_contact
            )
            db_name_datasoursInFirebase.collection("UserProfile")
                .add(User)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("TAG", "Error adding document", e)
                }
        }
    }

    fun uploadImage_TOFirebaseAND_return_LINK(userDataModel: UserDataModel): Flow<String> =
        callbackFlow {
            val storegeRifrensimages = Firebase.storage.reference

                val reference =
                    storegeRifrensimages.child("imageUser/${Calendar.getInstance().timeInMillis}")

                Log.e(
                    "TAG",
                    "uploadImage_TOFirebaseAND_return_LINK: ${userDataModel.data_User_image}"
                )
                val imageUri = reference.putFile(userDataModel.data_User_image.toUri())
                    .continueWithTask { task ->
                        /// reference =====
                        reference.downloadUrl
                    }.await() // await

                trySend(imageUri.toString())

            awaitClose { }
        }


    private fun getAllUsersFromFirebaseForShow() {

        try {
            val db = Firebase.firestore
            db.collection(" UserProfile")
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        return@addSnapshotListener
                    }
                    val UserDatafromFirebase = mutableListOf(UserDataModel())
                    snapshot?.documents?.forEach() {
                        if (it.exists()) {
                            val UserProfilGetValueFromFirebaseAsDataModel =
                                it.toObject(UserDataModel::class.java)
                            Log.e("TAG", "getAllUsersFromFirebaseForShow: ${it}")

                            UserDatafromFirebase.add(UserProfilGetValueFromFirebaseAsDataModel!!)

                        }
                    }
                    Log.e(
                        "TAG",
                        "getAllPromotionFromFirebaseForShoiiiiiiiii: ${UserDatafromFirebase}"
                    )


                    _Userlivedata.value = UserDatafromFirebase
                }
        } catch (exception: Exception) {

        }
    }

}