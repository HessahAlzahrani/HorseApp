package com.example.horseapp.addPromotion.uiLayer

import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.horseapp.dataLayer.UserDataModel
import com.example.horseapp.utilits.getUserId
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.*

class UserProfileViewModel : ViewModel() {


    val db = Firebase.firestore

    val _Userlivedata = MutableLiveData<List<UserDataModel>>()

    val _userInformation = MutableLiveData<UserDataModel>()
    //1 // craete db in fierbase
//    private val databaseUserinfirebase = Firebase.firestore.collection(
//        " UserDataModel"
//    )


    fun addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(userDataModel: UserDataModel) {
        viewModelScope.launch {
            add_DB_USERfromFirebase(userDataModel)
        }

    }

    suspend fun add_DB_USERfromFirebase(userDataModel: UserDataModel) {

        uploadImage_TOFirebaseAND_return_LINK(userDataModel).collect {

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
                .document(Firebase.auth.currentUser?.uid!!)
                .set(User)
                .addOnSuccessListener { documentReference ->
                    Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference}")
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

            val imageUri = reference.putFile(userDataModel.data_User_image.toUri())
                .continueWithTask { task ->
                    /// reference =====
                    reference.downloadUrl
                }.await() // await

            trySend(imageUri.toString())

            awaitClose { }
        }


    //create fun to coll suspend function
    fun getUserscreateFunToCollSuspendFunction() {
        viewModelScope.launch {
            getUsersFromFirebaseForShowByID().collect {
                _userInformation.value = it

            }
        }

    }

    suspend fun getUsersFromFirebaseForShowByID(): Flow<UserDataModel> = callbackFlow {
        try {
            val db = Firebase.firestore
            db.collection("UserProfile").document(Firebase.auth.currentUser?.uid!!)
                .addSnapshotListener { snapshot, exception ->
                    if (exception != null) {
                        return@addSnapshotListener
                    }

                    val user = snapshot?.toObject(UserDataModel::class.java)
                    //  Log.e("TAG", "getUsersFromFirebaseForShow: asdfg ${user}" )
                    trySend(user!!)
                }


        } catch (exception: Exception) {
            Log.e("Exception", "getAllProducts: ${exception.message.toString()}")

        }

        awaitClose {

        }
    }

        // function for delete user Account
    fun deletUserAccountFromFirebase() {
        db.collection("UserProfile").document(getUserId()) //coll function user ID()
            .delete()
            .addOnSuccessListener {
                Log.d("TAG", "DocumentSnapshot successfully deleted!")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error deleting document", e)
            }
    }
}

