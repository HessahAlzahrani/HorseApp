package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.horseapp.dataLayer.data
import com.example.horseapp.databinding.FragmentStartListBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartListFragment : Fragment() {

    private var binding: FragmentStartListBinding? = null



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStartListBinding.inflate(inflater, container, false)
        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val adapter = ItemListAdapter(requireContext())
        binding?.recyclerViewId?.adapter = adapter
        adapter.submitList(data.resultItemHours)


    }


    override fun onDestroy() {
        super.onDestroy()

        // garbage collector
        binding = null
    }





}