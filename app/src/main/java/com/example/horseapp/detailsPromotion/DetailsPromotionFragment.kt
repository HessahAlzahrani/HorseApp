package com.example.horseapp.detailsPromotion

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.horseapp.R
import com.example.horseapp.databinding.FragmentDetalsPromotionBinding


class DetailsPromotionFragment : Fragment(R.layout.fragment_detals_promotion) {

    private var binding: FragmentDetalsPromotionBinding? = null

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

        binding?.apply {
            textViewContentDetalsId.text = navigationArgs.itemContentArgument



//            binding!!.imageViewHorseDetailsFragmentId.setImageURI(navigationArgs.imageArgument.toUri())


            // thes for view image in fragment from dataSuors
            context?.let { Glide.with(it).load(navigationArgs.imageUrlArgument).into(binding?.imageViewHorseDetailsFragmentId!!) }


        }

        arguments.let {
            binding?.apply {


  //  index = it?.getInt("postion")

    textViewContentDetalsId.text = (it?.getString("itemNameArgument"))
    textViewContentDetalsId.text = it?.getString("sorteArguments")


}
        }
    }
}
