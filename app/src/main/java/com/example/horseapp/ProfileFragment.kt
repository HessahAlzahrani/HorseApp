package com.example.horseapp

import android.app.Notification
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.horseapp.dataLayer.UserDataSource
import com.example.horseapp.databinding.FragmentProfileBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth

class ProfileFragment : Fragment() {

    private var binding : FragmentProfileBinding?=null

    // sing in with firebase
    ///aryye of types of authentication like by phone, google, facebook
    //AuthUI.IdpConfig.GoogleBuilder().build()

    val providers = arrayListOf(

        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build())

    // take the providers then build
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (FirebaseAuth.getInstance().currentUser == null){
            signInLauncher.launch(signInIntent)
        }

        binding= FragmentProfileBinding.inflate(inflater, container, false)
        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        // return the information of the current signed in user
        val user = FirebaseAuth.getInstance().currentUser
     binding?.textViewNameInProfileId?.text  = user?.displayName
        binding?.textViewContentInProfileFragminteId?.text  = user?.email


        val image = UserDataSource.resultItemUser.get(0).data_User_image

        binding?.textViewNameInProfileId?.text = UserDataSource.resultItemUser.get(0).data_User_Name
        binding?.textViewContentInProfileFragminteId?.text = UserDataSource.resultItemUser.get(0).data_User_contact

//        context?.let { Glide.with(it).load(navigationArgs.imageUrlArgument)
//        .into(binding?.imageViewHorseDetailsFragmentId!!) }










context?.let { Glide.with(it).load(image).into(binding?.imageNameInProfileId!!) }



//       binding?.buttonAddInProfileFragmintId?.setOnLongClickListener {
//            MaterialAlertDialogBuilder(this.requireContext())
//                .setTitle("sdfgh")
//                .setMessage("")
//                .setCancelable(false)
//                .setNegativeButton("No") { _, _ -> }
//                .setPositiveButton("Yes") { _, _ ->}
//
//                ////  using function deleteItem() on Dialog #####
//                .setPositiveButton("Yes") { _, _ ->
//                  signOut()
//                }
//                .show()
//
//            true
//        }
        /**
        // binding for navigation from profile for addFragment
        */

        binding?.imageViewAddInProfileId?.setOnClickListener{
            val action = ProfileFragmentDirections.actionProfileFragmentToAddPromotionFragment()
            findNavController().navigate(action)

        binding?.buttonSignOutInProfileFragmintId?.setOnLongClickListener {
            signOut()
            Toast.makeText(this.requireContext(), "Signed out successfully", Toast.LENGTH_SHORT)
                .show()
            true }
        }

    }



    /**
////check if the user signed in or not #####
*/
    override fun onStart() {
        super.onStart()



    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
//            findNavController(R.id.action)


        } else {
            println("none")
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this.requireContext())


    }


}