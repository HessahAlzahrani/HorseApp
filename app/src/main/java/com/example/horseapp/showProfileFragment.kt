package com.example.horseapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horseapp.databinding.FragmentShowProfileBinding


class showProfileFragment : Fragment() {

  private var binding : FragmentShowProfileBinding? = null


  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {

    binding = FragmentShowProfileBinding.inflate(inflater,container,false)
    return binding?.root
  }
}// end class