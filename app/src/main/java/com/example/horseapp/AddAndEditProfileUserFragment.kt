package com.example.horseapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.horseapp.addPromotion.uiLayer.UserProfileViewModel
import com.example.horseapp.dataLayer.UserDataModel
import com.example.horseapp.databinding.FragmentAddAndEditUserProfileBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import java.util.zip.Inflater


class AddAndEditProfileUserFragment : Fragment() {

    private var binding: FragmentAddAndEditUserProfileBinding? = null
    private val UserProfileViewModel: UserProfileViewModel by activityViewModels()
    private var PICK_IMAGES_CODE = 0
    private val pic_id = 123

    // file for hold image from GAILY
    var fileImageFromGAILY : Uri? = null


   //1 // sing in with firebase
    ///aryye of types of authentication like by phone, google, facebook
    //AuthUI.IdpConfig.GoogleBuilder().build()

    val providers = arrayListOf(

        AuthUI.IdpConfig.EmailBuilder().build(),
        AuthUI.IdpConfig.PhoneBuilder().build(),
        AuthUI.IdpConfig.GoogleBuilder().build()
    )


    //2// build providers  like : ( AuthUI.IdpConfig.GoogleBuilder().build()........
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()

    //3// this function for firebase Auth
    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    )
    {
            res -> this.onSignInResult(res)
    }



    fun validationUI(name: String, content: String,Contact:String,City:String): Boolean {
        var result = true
        if (name.isEmpty()) {
            binding?.editTextUserNameAddEditUserProfileID?.error = "Enter the name"
            result = false
        } else {
            binding?.editTextUserNameAddEditUserProfileID?.error = null
        }
        if (content.isEmpty()) {
            binding?.editTextContentAddEdittUserProfileID?.error = "Enter the content"
            result = false
        } else {
            binding?.editTextContentAddEdittUserProfileID?.error = null
        }

        if (City.isEmpty()) {
            binding?.editTextCityAddEdittUserProfileID?.error = "Enter the content"
            result = false
        } else {
            binding?.editTextCityAddEdittUserProfileID?.error = null
        }

        if (Contact.isEmpty()) {
            binding?.editTextContactAddEdittUserProfileID?.error = "Enter the content"
            result = false
        } else {
            binding?.editTextContactAddEdittUserProfileID?.error = null
        }
        if (fileImageFromGAILY == null) {
            Toast.makeText(this.requireContext(), "ADD IMAGE !!", Toast.LENGTH_SHORT).show()
            result = false
        }

        return result
    }


    /// function Result sign in
    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == AppCompatActivity.RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)


            inflater.inflate(R.menu.menu_in_profile_fragment, menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId) {
            R.id.sign_OUT_inPtofile_id -> {
                showDialogForSignOUT()
                findNavController().navigate(R.id.startListFragment2)
            }

            R.id.addPromotion_in_profile_id -> {
                findNavController().navigate(R.id.addPromotionFragment)

            }
            R.id.move_fragment_menu_list_ID -> {
                findNavController().navigate(R.id.myListFragmentUser)
            }


            R.id.setting_inPtofile_ID -> {
                findNavController().navigate(R.id.userPrefrenceFragment)
            }


            R.id.delete_inPtofile_ID -> {  //coll function for using
                showDialogForDeletUSerAccount()
            }

        }
        return super.onOptionsItemSelected(item)

    }


    //intent for GAILY
    private fun intentPickImagesFromGaily() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        try {
            fileImageFromGAILY = data?.data!!
            fileImageFromGAILY.toString()
        }catch (e: Exception){

        }



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        if (FirebaseAuth.getInstance().currentUser == null) {
            signInLauncher.launch(signInIntent)
        }
        // Inflate the layout for this fragment
        binding = FragmentAddAndEditUserProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        try {
            UserProfileViewModel.getUserscreateFunToCollSuspendFunction()
            UserProfileViewModel._userInformation.observe(viewLifecycleOwner, {
                binding?.editTextUserNameAddEditUserProfileIDText?.setText(it.data_User_Name)
                binding?.editTextContentAddEdittUserProfileIDText?.setText(it.data_User_content)
                binding?.editTextCityAddEdittUserProfileIDText?.setText(it.data_city_user)
                binding?.editTextContactAddEdittUserProfileIDText?.setText(it.data_User_contact)

                Glide.with(this.requireContext()).load(it.data_User_image).circleCrop().into(binding?.imageViewUsirProfileInAddEditID!!)

            })

        }catch (e: Exception){

        }

        binding?.BUTTONADDUserProfileAddEdit?.setOnClickListener {

            val nameUser = binding?.editTextUserNameAddEditUserProfileIDText?.text.toString()
            val contentUser = binding?.editTextContentAddEdittUserProfileIDText?.text.toString()
            val cityUser = binding?.editTextCityAddEdittUserProfileIDText?.text.toString()
            val contactUser = binding?.editTextContactAddEdittUserProfileIDText?.text.toString()

            if (validationUI(nameUser,contentUser,contactUser,cityUser)) {
                Log.e("TAG", "onViewCreated: Enter")
                UserProfileViewModel.addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(
                    UserDataModel(
                        data_User_image = fileImageFromGAILY.toString(),//fileImageFromGAILY = fileImageFromGAILY
                        data_User_Name = nameUser,
                        data_User_content = contentUser,
                        data_city_user = cityUser,
                        data_User_contact = contactUser,
                    )
                )
            }
        }

        binding?.imageViewUsirProfileInAddEditID?.setOnLongClickListener{
            Log.e("TAG", "onViewCreatedvbb: ", )
            intentPickImagesFromGaily()
            true
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this.requireContext())
    }


    private fun showDialogForSignOUT() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                signOut()
                // delete

            }
            .show()
    }


    private fun showDialogForDeletUSerAccount() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(android.R.string.dialog_alert_title))
            .setMessage(getString(R.string.delete_question_account))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.no)) { _, _ -> }
            .setPositiveButton(getString(R.string.yes)) { _, _ ->
                UserProfileViewModel.deletUserAccountFromFirebase()
                signOut()
                findNavController().navigate(R.id.startListFragment2)



            }
            .show()
    }

}

