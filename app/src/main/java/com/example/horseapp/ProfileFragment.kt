package com.example.horseapp

import android.app.Notification
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.horseapp.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.actionCodeSettings

class ProfileFragment : Fragment() {

    private var binding: FragmentProfileBinding? = null

    // sing in with firebase
    ///aryye of types of authentication like by phone, google, facebook
    //AuthUI.IdpConfig.GoogleBuilder().build()

    val providers = arrayListOf(

        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )

    // build providers  like : ( AuthUI.IdpConfig.GoogleBuilder().build()........
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()

        // this function for firebase Auth
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    )
    {
            res -> this.onSignInResult(res)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // if User sign in before or not
        if (FirebaseAuth.getInstance().currentUser == null) {
            signInLauncher.launch(signInIntent)
        }
        //after sign in  binding result with fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        //show information User of the current signed
        val user = FirebaseAuth.getInstance().currentUser
        binding?.textViewNameInProfileId?.text = user?.displayName
        binding?.textViewContentInProfileFragminteId?.text = user?.email

        // Button add promotion from profile
        binding?.imageViewAddInProfileId?.setOnClickListener {
            val action = ProfileFragmentDirections.actionProfileFragmentToAddPromotionFragment()
            findNavController().navigate(action)

        }
        /// sign out Button
        binding?.buttonSignOutInProfileFragmintId?.setOnLongClickListener {
            signOut()
            Toast.makeText(this.requireContext(), "Signed out successfully", Toast.LENGTH_SHORT)
                .show()
            true
        }
        /// Button Setting User Profile
        binding?.iConSettingUserPrifileID?.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_addAndEditProfileUserFragment)
        }


    }//end fun onViewCreated
//
//    /**
//    ////check if the user signed in or not #####
//     */
//    override fun onStart() {
//        super.onStart()


//    }

        /// function Result sign in
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this.requireContext())


    }


}