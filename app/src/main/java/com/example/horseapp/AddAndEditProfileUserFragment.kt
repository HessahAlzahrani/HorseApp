package com.example.horseapp

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.horseapp.addPromotion.uiLayer.UserProfileViewModel
import com.example.horseapp.dataLayer.UserDataModel
import com.example.horseapp.databinding.FragmentAddAndEditUserProfileBinding


class AddAndEditProfileUserFragment : Fragment() {

    private var binding: FragmentAddAndEditUserProfileBinding? = null
    private val UserProfileViewModel: UserProfileViewModel by activityViewModels()
    private var PICK_IMAGES_CODE = 0
    private val pic_id = 123

    // file for hold image from GAILY
    var fileImageFromGAILY : Uri? = null


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
        Glide.with(this.requireContext()).load(fileImageFromGAILY).circleCrop().into(binding?.imageViewUsirProfileInAddEditID!!)
       // binding?.imageViewUsirProfileInAddEditID?.setImageURI(fileImageFromGAILY)
        fileImageFromGAILY.toString()


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddAndEditUserProfileBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        UserProfileViewModel.getUserscreateFunToCollSuspendFunction()

        UserProfileViewModel._userInformation.observe(viewLifecycleOwner, {
            binding?.editTextUserNameAddEditUserProfileID?.setText(it.data_User_Name)
            binding?.editTextContentAddEdittUserProfileID?.setText(it.data_User_content)
            binding?.editTextCityAddEdittUserProfileID?.setText(it.data_city_user)
            binding?.editTextContactAddEdittUserProfileID?.setText(it.data_User_contact)

            Glide.with(this.requireContext()).load(it.data_User_image).circleCrop().into(binding?.imageViewUsirProfileInAddEditID!!)

        })

        binding?.BUTTONADDUserProfileAddEdit?.setOnClickListener {

            val nameUser = binding?.editTextUserNameAddEditUserProfileID?.text.toString()
            val contentUser = binding?.editTextContentAddEdittUserProfileID?.text.toString()
            val cityUser = binding?.editTextCityAddEdittUserProfileID?.text.toString()
            val contactUser = binding?.editTextContactAddEdittUserProfileID?.text.toString()

//            fileImageFromGAILY = fileImageFromGAILY

            UserProfileViewModel.addFunToCallSuspendFunAddHorseFun_FORUSINGINIT(UserDataModel(
               data_User_image = fileImageFromGAILY.toString(),
                data_User_Name = nameUser,
                data_User_content = contentUser,
                data_city_user = cityUser,
                data_User_contact = contactUser,
            ))



//
        }


        binding?.imageViewUsirProfileInAddEditID?.setOnLongClickListener{
            Log.e("TAG", "onViewCreatedvbb: ", )
            intentPickImagesFromGaily()
            true
        }


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
