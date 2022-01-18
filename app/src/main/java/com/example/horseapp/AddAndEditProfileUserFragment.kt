package com.example.horseapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
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
                showConfirmationDialog()
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

            // R.
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

        fileImageFromGAILY = data?.data!!
      //  Glide.with(this.requireContext()).load(fileImageFromGAILY).circleCrop().into(binding?.imageViewUsirProfileInAddEditID!!)
       // binding?.imageViewUsirProfileInAddEditID?.setImageURI(fileImageFromGAILY)
        fileImageFromGAILY.toString()


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

        UserProfileViewModel.getUserscreateFunToCollSuspendFunction()
//
        UserProfileViewModel._userInformation.observe(viewLifecycleOwner, {
            binding?.editTextUserNameAddEditUserProfileIDText?.setText(it.data_User_Name)
            binding?.editTextContentAddEdittUserProfileIDText?.setText(it.data_User_content)
            binding?.editTextCityAddEdittUserProfileIDText?.setText(it.data_city_user)
            binding?.editTextContactAddEdittUserProfileIDText?.setText(it.data_User_contact)

            Glide.with(this.requireContext()).load(it.data_User_image).circleCrop().into(binding?.imageViewUsirProfileInAddEditID!!)

        })

//        binding?.BUTTONADDUserProfileAddEdit?.setOnClickListener {
//
//            val nameUser = binding?.editTextUserNameAddEditUserProfileIDText?.text.toString()
//            val contentUser = binding?.editTextContentAddEdittUserProfileIDText?.text.toString()
//            val cityUser = binding?.editTextCityAddEdittUserProfileIDText?.text.toString()
//            val contactUser = binding?.editTextContactAddEdittUserProfileID?.text.toString()
//
////            fileImageFromGAILY = fileImageFromGAILY
//
//            UserProfileViewModel.addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(UserDataModel(
//               data_User_image = fileImageFromGAILY.toString(),
//                data_User_Name = nameUser,
//                data_User_content = contentUser,
//                data_city_user = cityUser,
//                data_User_contact = contactUser,
//            ))
//        }
//        binding?.imageViewUsirProfileInAddEditID?.setOnLongClickListener{
//            Log.e("TAG", "onViewCreatedvbb: ", )
//            intentPickImagesFromGaily()
//            true
//        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this.requireContext())
    }


    private fun showConfirmationDialog() {
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


}



//    //1 // craete db in fierbase
//     private val databaseUserinfirebase =Firebase.firestore.collection(
//         " UserDataModel"
//     )
//
//
//    //2/// return collection above val 1
//   private fun holdInfoFromXml () : UserDataModel {
//        var userIDD = Firebase.auth.currentUser!!.uid
//        var imageUserinADDprofile = binding?.imageViewUsirProfileInAddEditID.toString()
//        var UserNameInProfileADD = binding?.editTextUserNameAddEditUserProfileID?.text.toString()
//        val UserContentInProfileADD = binding?.editTextContentAddEdittUserProfileID?.text.toString()
//        val UserCityInProfileADD = binding?.editTextCityAddEdittUserProfileID?.text.toString()
//        val UserContactInProfileADD =binding?.editTextContactAddEdittUserProfileID?.text.toString()
//
//        return UserDataModel(
//            userId = userIDD,
//            data_User_image = imageUserinADDprofile,
//            data_User_Name = UserNameInProfileADD,
//            data_User_content = UserContentInProfileADD,
//            data_city_user = UserCityInProfileADD,
//            data_User_contact = UserContactInProfileADD
//        )
//        // UserDataModel ("djvkf.jpg","Jana")
//   }
//
//        //3 //
//    private fun savaDataInFierStorfromUser (user : UserDataModel){
//        databaseUserinfirebase.add(user)
//            .addOnCompleteListener {task ->
//                if (task.isSuccessful)
//                    Toast.makeText(this.requireContext(), "Save", Toast.LENGTH_SHORT).show()
//            }
//
//    }


//
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//
//        //4//
//        binding?.BUTTONADDUserProfileAddEdit?.setOnClickListener {
//            val user = holdInfoFromXml ()
//            savaDataInFierStorfromUser(user)
//
//        }
//    }
