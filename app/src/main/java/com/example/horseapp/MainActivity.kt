package com.example.horseapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.horseapp.databinding.ActivityMainBinding
import com.example.horseapp.prefrence.ThemeApplication
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding
    var signIn = false


    override fun onCreate(savedInstanceState: Bundle?) {
        ThemeApplication()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //  setContentView(R.layout.activity_main)

        val bottomNavigationId = binding.bottomNavID
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerViewss) as NavHostFragment
        navController = navHostFragment.navController
        bottomNavigationId.setupWithNavController(navController)

        binding.bottomNavID.menu.findItem(R.id.addAndEditProfileUserFragment)
            ?.setOnMenuItemClickListener{
                if (Firebase.auth.currentUser == null) {
                    Toast.makeText(this, "please sign in", Toast.LENGTH_SHORT).show()
                    navController.navigate(R.id.signInFragment)
                    signIn = true
                } else if (Firebase.auth.currentUser != null) {
                    signIn = false

                }

               signIn
            }

    }





}