package com.example.horseapp.addPromotion.uiLayer

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.horseapp.R
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.databinding.FragmentAddPromotionBinding
import java.io.File


private const val Request_code = 42
private var photoFile: File = File.createTempFile("image/photo/image", ".jpg", File("com.example.horseapp"))

class AddPromotionFragment : Fragment() {
    private var binding: FragmentAddPromotionBinding? = null


    //add viewModel :
    private val horsesViewModel: HorsesViewModel by activityViewModels()

    //store user picked images
//    private var images: ArrayList<Uri?>? = null

    //current position/index of selected image
    private var position = 0

    //request code to pick images
    private var PICK_IMAGES_CODE = 0

    private val pic_id = 123


    private fun pickImagesIntint() {
        val intent = Intent()
        intent.type = "image/*"
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Image(s)"), PICK_IMAGES_CODE)

    }


    // fun for intent inside code (Request and result )
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e("TAG", "onActivityResult: in", )
        if (requestCode == PICK_IMAGES_CODE) {

            if (data!!.clipData != null) {

                /** picked multiple images
                // get number of picked images*/

                val count = data.clipData!!.itemCount
                for (i in 0 until count) {

                    val imageUri = data.clipData!!.getItemAt(i).uri
                    /**
                     * add image to list
                     **/


                    horsesViewModel.imageList!!.add(imageUri)
                    Log.e("TAG", "onActivityResulttttttt: ${horsesViewModel.imageList}")

                }
                /**set first image from list to image switcher*/
                binding?.ImageSwitcherInAddFragmentId?.setImageURI(horsesViewModel.imageList!![0])
                position = 0
            } else {
                /**pick single image*/

                val imageUri = data.data
                //set image to image switcher
                binding?.ImageSwitcherInAddFragmentId?.setImageURI(imageUri)
                position = 0
            }


            if (requestCode == Activity.RESULT_OK) {



            }
        }
        if (requestCode == Request_code ) {
            Log.e("TAG", "onActivityResult: in if Bitmap", )
            val takenImage = BitmapFactory.decodeFile(photoFile.absolutePath)
            // binding?.ImageSwitcherInAddFragmentId?.set(takenImage)

        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }

        if (requestCode == pic_id) {
            val photo = data?.extras
                ?.get("data") as Uri?

            // Set the image in imageview for display
            binding?.ImageSwitcherInAddFragmentId?.setImageURI(photo)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPromotionBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addButtonAddFragmentId?.setOnClickListener {
            Log.e("TAG", "onViewCreated: in", )

            val name = binding?.editTextTextPersonNameId?.text.toString()

            horsesViewModel.addHorsefun(
                HorsesDataModel(
                    Data_horse_Name = name,
                    data_horse_Content = "ghhh",
                    Data_horse_image = horsesViewModel.imageList?.get(0).toString()
                )
            )

            findNavController().navigate(R.id.action_addPromotionFragment_to_startListFragment2)
        }

        binding?.ImageSwitcherInAddFragmentId?.setFactory { ImageView(this.requireActivity()) }
        binding?.iconNXETId?.setOnClickListener {

            if (position < horsesViewModel.imageList!!.size - 1) {
                position++
                binding?.ImageSwitcherInAddFragmentId?.setImageURI(horsesViewModel.imageList!![position])
            } else {
                //No more images
                Toast.makeText(this.requireContext(), "No More images ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding?.iconPreviousId?.setOnClickListener {

            if (position > 0) {
                position--
                binding?.ImageSwitcherInAddFragmentId?.setImageURI(horsesViewModel.imageList!![position])
            } else {
                //No more images
                Toast.makeText(this.requireContext(), "No More images ", Toast.LENGTH_SHORT)
                    .show()
            }
        }
        binding?.iconAddPhotoGId?.setOnClickListener {
            pickImagesIntint()
        }

        /**
         * camera cod intent
         * */
        binding?.iconOpenCameraId?.setOnClickListener {
//            val camera_intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//            startActivityForResult(camera_intent, pic_id)
            Log.e("TAG", "onViewCreated: cameraID", )
                val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (takePictureIntent.resolveActivity(this.requireContext().packageManager) != null) {
                    startActivityForResult(takePictureIntent, Request_code)
                } else {
                    //No more images
                    Toast.makeText(
                        this.requireContext(),
                        "unable to open camera ",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        // garbage collector
        binding = null
    }


}



