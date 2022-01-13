package com.example.horseapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.horseapp.addPromotion.uiLayer.UserProfileViewModel
import com.example.horseapp.databinding.FragmentShowProfileBinding


class ShowProfileFragment : Fragment() {

  private var binding : FragmentShowProfileBinding? = null
  private val userProfileViewModel: UserProfileViewModel by viewModels()


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentShowProfileBinding.inflate(inflater,container,false)
    return binding?.root
  }


  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    Log.e("TAG", "onViewCreated: ${userProfileViewModel._Userlivedata.value}", )

  }
}// end class