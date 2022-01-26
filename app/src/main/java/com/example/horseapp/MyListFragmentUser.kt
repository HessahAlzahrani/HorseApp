package com.example.horseapp

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.horseapp.addPromotion.uiLayer.HorsesViewModel
import com.example.horseapp.databinding.FragmentMyListUserBinding
import kotlinx.coroutines.launch

class MyListFragmentUser : Fragment() {

    private var binding: FragmentMyListUserBinding? = null

    private val horsesViewModel: HorsesViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMyListUserBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        horsesViewModel._userPromotion.observe(viewLifecycleOwner, {
        })
        val adapter = ListUserAdapter(requireContext())
        binding?.listUserIDRecyclerView?.adapter = adapter

        //use all item in viewmodel
        lifecycleScope.launch {
            horsesViewModel._horsesUsserlivedata.observe(viewLifecycleOwner, {
                adapter.submitList(it)
            })
        }
    }


    override fun onDestroy() {
        super.onDestroy()

        // garbage collector
        binding = null
    }
}