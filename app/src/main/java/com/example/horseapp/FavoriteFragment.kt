package com.example.horseapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.horseapp.databinding.FragmentFavoriteBinding

class FavoriteFragment : Fragment() {

   private var binding:FragmentFavoriteBinding?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


//**
        // Inflate the layout for this fragment:
        //**
        binding= FragmentFavoriteBinding.inflate(inflater,container,false)
        return binding?.root

    }






}