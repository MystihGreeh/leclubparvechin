package com.example.leclubparvechin.repository

import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await

class AuthRepository {
    private val auth: FirebaseAuth = FirebaseAuth.getInstance()

    // Connexion utilisateur
    suspend fun login(email: String, password: String): Result<Unit> {
        return try {
            auth.signInWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Inscription utilisateur
    suspend fun register(email: String, password: String): Result<Unit> {
        return try {
            auth.createUserWithEmailAndPassword(email, password).await()
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Vérifie si l'utilisateur est connecté
    fun isUserLoggedIn(): Boolean = auth.currentUser != null

    // Déconnexion utilisateur (facultatif, au cas où tu voudrais l'utiliser)
    fun logout() {
        auth.signOut()
    }
}
