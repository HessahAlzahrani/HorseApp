package com.example.horseapp.utilits

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//type here any function for using any wi


fun getUserId(): String {
    return Firebase.auth.currentUser?.uid.toString()
}
