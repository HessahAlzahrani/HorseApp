package com.example.horseapp.utilits

import android.text.TextUtils
import com.example.horseapp.R
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


//type here any function for using any wi


fun getUserId(): String {
    return Firebase.auth.currentUser?.uid.toString()
}

