package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.horseapp.R
import com.example.horseapp.dataLayer.HorsesDataModel
import com.example.horseapp.dataLayer.HorsesDataSource
import com.example.horseapp.databinding.FragmentAddPromotionBinding


class AddPromotionFragment : Fragment() {
    private var binding: FragmentAddPromotionBinding? = null
    //add viewModel :
    private val horsesViewModel: HorsesViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPromotionBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated( view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.addButtonAddFragmentId?.setOnClickListener{

            val name = binding?.editTextTextPersonNameId?.text.toString()

            horsesViewModel.addHorsefun(HorsesDataModel(
                Data_horse_Name = name,
                data_horse_Content = "ghhh",
                Data_horse_image = "" ))



//            HorsesDataSource().addPromotionHorses(HorsesDataModel(
//                binding?.editTextTextPersonNameId?.text.toString(),
//                binding?.sorting1GroupId?.textAlignment.toString(),
//                binding?.sorting2GroupId?.textAlignment.toString(),
//                binding?.
//
//                false
//            ))

            findNavController().navigate(R.id.action_addPromotionFragment_to_startListFragment2)
        }

    }
    override fun onDestroy() {
        super.onDestroy()

        // garbage collector
        binding = null
    }

}