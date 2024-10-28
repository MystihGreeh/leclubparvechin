package com.example.leclubparvechin.view

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.leclubparvechin.R
import com.example.leclubparvechin.repository.AuthRepository
import com.example.leclubparvechin.viewmodel.AuthState
import com.example.leclubparvechin.viewmodel.AuthViewModel
import com.example.leclubparvechin.viewmodel.AuthViewModelFactory

class LoginActivity : AppCompatActivity() {

    private lateinit var authViewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val emailEditText = findViewById<EditText>(R.id.emailEditText)
        val passwordEditText = findViewById<EditText>(R.id.passwordEditText)
        val loginButton = findViewById<Button>(R.id.loginButton)

        // Créer une instance de AuthRepository
        val authRepository = AuthRepository()

        // Utiliser le ViewModelFactory pour obtenir une instance de AuthViewModel
        val factory = AuthViewModelFactory(authRepository)
        authViewModel = viewModels<AuthViewModel> { factory }.value

        // Observer l'état de connexion
        lifecycleScope.launchWhenStarted {
            authViewModel.authState.collect { state ->
                when (state) {
                    is AuthState.Loading -> showLoading(true)
                    is AuthState.Success -> navigateToMainActivity()
                    is AuthState.Failure -> {
                        showLoading(false)
                        Toast.makeText(this@LoginActivity, state.errorMessage, Toast.LENGTH_LONG).show()
                    }
                    else -> Unit
                }
            }
        }

        // Gérer le clic sur le bouton de connexion
        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            authViewModel.login(email, password)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        // Mettre à jour l'interface en fonction de l'état de chargement
    }

    private fun navigateToMainActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
