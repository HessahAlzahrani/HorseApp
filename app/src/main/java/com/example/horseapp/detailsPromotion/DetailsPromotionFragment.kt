package com.example.horseapp.detailsPromotion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.net.toUri
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.horseapp.R
import com.example.horseapp.databinding.FragmentDetalsPromotionBinding
import kotlinx.android.synthetic.main.fragment_favorite.*
import java.util.ArrayList


class DetailsPromotionFragment : Fragment(R.layout.fragment_detals_promotion) {

    private var binding: FragmentDetalsPromotionBinding? = null
    private var position = 0
    var imageList: List<String> = listOf()

    //the line for move data from next fragment (in fragment go)
    private val navigationArgs: DetailsPromotionFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetalsPromotionBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding?.root!!
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var index: Int? = 0

        imageList = navigationArgs.imageUrlArgument.toList()

        Log.e("TAG", "onViewCreated: it$imageList", )


       // Glide.with(this.requireContext()).load(imageList).into(binding?.imageViewHorseDetailsFragmentId)
        binding?.apply {
            textViewDetailsNameHorsesId.text = navigationArgs.itemNameArgument
            textViewContentDetalsId.text = navigationArgs.itemContentArgument


//            binding?.imageViewHorseDetailsFragmentId?.setImageURI(imageList!![position]?.toUri())

          //  binding?.imageViewHorseDetailsFragmentId?.setFactory { ImageView(this@DetailsPromotionFragment.requireActivity()) }
            binding?.iconNXETId?.setOnClickListener {
                Log.e("TAG", "onViewCreated:hhhh $imageList")


                if (position < imageList.size - 1) { position++
                    Log.e("TAG", "onViewCreated:ggggggggggggg $imageView")
                    Glide.with(this@DetailsPromotionFragment.requireContext()).load(imageList[0]).into(binding?.imageViewHorseDetailsFragmentId!!)



//                    binding?.imageViewHorseDetailsFragmentId?.setImageURI(imageList!![position]?.toUri())
                }
                else {
                    //No more images
                    Toast.makeText(this@DetailsPromotionFragment.requireContext(), "No More images ", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            // this ID icon for search photo before  ADD====
            binding?.iconPreviousId?.setOnClickListener {
                if (position > 0) { position--

                    Glide.with(this@DetailsPromotionFragment.requireContext()).load(imageList[0]).into(binding?.imageViewHorseDetailsFragmentId!!)                } else {
                    //No more images
                    Toast.makeText(this@DetailsPromotionFragment.requireContext(), "No More images ", Toast.LENGTH_SHORT)
                        .show()
                }
            }


//           binding!!.imageViewHorseDetailsFragmentId.setImageURI(navigationArgs.imageArgument.toUri())


            // thes for view image in fragment from dataSuors
         //   context?.let { Glide.with(it).load(navigationArgs.imageUrlArgument).into(binding?.imageViewHorseDetailsFragmentId!!) }


        }
//
//        arguments.let {
//            binding?.apply {
//
//
//                // for make index .. index = it?.getInt("position")
//                textViewContentDetalsId.text = it?.getString("itemNameArgument")
//                textViewContentDetalsId.text = it?.getString("sorteArguments")
//            }
//
//        }
                /***
                 *  make photo button action for profile Show
                 *  */
        binding?.imageProfileInDetailsId?.setOnClickListener{
            val action = DetailsPromotionFragmentDirections.actionDetailsPromotionFragmentToShowProfileFragment()
            findNavController().navigate(action)
        }
    }

}
