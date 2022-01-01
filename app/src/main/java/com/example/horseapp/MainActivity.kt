package com.example.horseapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.horseapp.databinding.ActivityMainBinding
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    lateinit var binding :ActivityMainBinding



    // sing in with firebase
    ///aryye of types of authentication like by phone, google, facebook
    //AuthUI.IdpConfig.GoogleBuilder().build()

    val providers = arrayListOf(

       AuthUI.IdpConfig.EmailBuilder().build(),

       AuthUI.IdpConfig.PhoneBuilder().build(),
       AuthUI.IdpConfig.GoogleBuilder().build())


   // take the providers then build
    val signInIntent = AuthUI.getInstance()
        .createSignInIntentBuilder()
        .setAvailableProviders(providers)
        .build()


    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
      //  setContentView(R.layout.activity_main)

val bottomNavigation = binding.bottomNavID
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerViewss) as NavHostFragment
        navController = navHostFragment.navController

        bottomNavigation.setupWithNavController(navController)
        // Set up the action bar for use with the NavController
//        setupActionBarWithNavController(navController)


//        binding?.imageView2?.setOnClickListener{
//            signInLauncher.launch(signInIntent)
//        }
//
//        binding?.imageView?.setOnClickListener {
//            signOut()
//        }


    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }


    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        if (result.resultCode == RESULT_OK) {
            val user = FirebaseAuth.getInstance().currentUser
//            findNavController(R.id.action)
            println(user?.uid)
        } else {
            println("none")
        }
    }

    private fun signOut() {
        AuthUI.getInstance()
            .signOut(this)

    }
}