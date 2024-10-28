package com.example.leclubparvechin.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.leclubparvechin.R
import com.example.leclubparvechin.repository.AuthRepository
import com.example.leclubparvechin.viewmodel.AuthViewModel
import com.example.leclubparvechin.viewmodel.AuthViewModelFactory
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Crée une instance de AuthRepository
        val authRepository = AuthRepository() // Assurez-vous de l'initialiser correctement ici

        // Crée le ViewModelFactory
        val factory = AuthViewModelFactory(authRepository)

        // Obtiens le ViewModel avec le factory
        authViewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        // Vérifie l'état de connexion
        if (authViewModel.isUserLoggedIn()) {
            // L'utilisateur est déjà connecté, lance MainActivity
            startActivity(Intent(this, MainActivity::class.java))
        } else {
            // L'utilisateur n'est pas connecté, lance LoginActivity
            startActivity(Intent(this, LoginActivity::class.java))
        }

        // Termine l'activité Splash pour éviter que l'utilisateur ne revienne à cet écran
        finish()
    }

    /*override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Utilisation de lifecycleScope pour attendre 2 secondes
        lifecycleScope.launch {
            delay(2000) // Attendre 2 secondes
            startActivity(Intent(this@SplashActivity, MainActivity::class.java))
            finish()
        }
    }*/
}