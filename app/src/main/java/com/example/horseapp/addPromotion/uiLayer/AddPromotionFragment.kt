package com.example.horseapp.addPromotion.uiLayer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.horseapp.databinding.FragmentAddPromotionBinding


class AddPromotionFragment : Fragment() {
    private var binding: FragmentAddPromotionBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddPromotionBinding.inflate(inflater, container, false)
        return binding?.root
    }



    override fun onDestroy() {
        super.onDestroy()

        // garbage collector
        binding = null
    }

}