package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.horseapp.databinding.FragmentStartListBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class StartListFragment : Fragment() {

    private var binding: FragmentStartListBinding? = null
    //add viewModel :
    private val horsesViewModel: HorsesViewModel by activityViewModels()


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

        //use all item in viewmodel
        horsesViewModel.allItemfromdatasuorse.observe(viewLifecycleOwner, {
            Log.e("TAG", "onViewCreated1111111111111111: $it", )
            adapter.submitList(it)
        })


        //from data sours directly :
//        adapter.submitList(HorsesDataSource.resultItemHours)


    }


    override fun onDestroy() {
        super.onDestroy()

        // garbage collector
        binding = null
    }





}