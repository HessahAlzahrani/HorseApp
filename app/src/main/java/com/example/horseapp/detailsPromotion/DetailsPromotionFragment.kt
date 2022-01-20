package com.example.horseapp.detailsPromotion

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.horseapp.R
import com.example.horseapp.databinding.FragmentDetalsPromotionBinding
import kotlinx.android.synthetic.main.fragment_favorite.*


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

        try {
            Glide.with(this.requireContext()).load(imageList[0]).into(binding?.imageViewHorseDetailsFragmentId!!)

        }catch (e: Exception){

        }

        Log.e("TAG", "onViewCreatedqwertyuio00000000000000000: ${navigationArgs.itemContentArgument}", )


        binding?.apply {
            textViewDetailsNameHorsesId.text = navigationArgs.itemNameArgument
            textViewContentDetalsId.text = navigationArgs.itemContentArgument


            binding?.iconNXETId?.setOnClickListener {
                Log.e("TAG", "onViewCreated:hhhh $imageList")


                if (position < imageList.size) {
                    Glide.with(this@DetailsPromotionFragment.requireContext())
                        .load(imageList[position]).into(binding?.imageViewHorseDetailsFragmentId!!)
                    position++



                    if (position < imageList.size) {
                        Glide.with(this@DetailsPromotionFragment.requireContext())
                            .load(imageList[position]).into(
                                binding?.imageViewHorseDetailsFragmentId!!
                            )
                        position++


                    } else {
                        //No more images
                        Toast.makeText(
                            this@DetailsPromotionFragment.requireContext(),
                            "No More images ",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
                // this ID icon for search photo before  ADD====
                binding?.iconPreviousId?.setOnClickListener {
                    if (position > 0) {
                        position--

                        Glide.with(this@DetailsPromotionFragment.requireContext())
                            .load(imageList[position])
                            .into(binding?.imageViewHorseDetailsFragmentId!!)

                    } else {
                        //No more images
                        Toast.makeText(
                            this@DetailsPromotionFragment.requireContext(),
                            "No More images ",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }

            }

            /***
             *  make photo button action for profile Show
             *  */
            binding?.imageViewHorseDetailsFragmentId?.setOnClickListener {
                val action =
                    DetailsPromotionFragmentDirections.actionDetailsPromotionFragmentToShowProfileFragment()
                findNavController().navigate(action)
            }
        }

    }
}
