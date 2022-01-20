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


enum class InputTypes { NAME, CITY }


fun TextInputLayout.isValid(textInput: TextInputEditText, validationTypes: InputTypes): Boolean {
    val text = textInput.text.toString()
    when (validationTypes) {
        InputTypes.NAME -> {
            if (TextUtils.isEmpty(text)) {
                this.error = this.context.getString(R.string.not_empty_field)
                return false
            }
        }
        InputTypes.CITY -> {
            if (TextUtils.isEmpty(text)) {
                this.error = this.context.getString(R.string.not_empty_field)
                return false
            }

        }
    }

    this.error = null
    return true

}