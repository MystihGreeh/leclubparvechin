package com.example.leclubparvechin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.leclubparvechin.model.game.Game
import com.example.leclubparvechin.repository.GameRepository


class GameViewModel(private val gameRepository: GameRepository) : ViewModel() {

    // Gère la création, l'affichage et la gestion des parties
    private val _games = MutableLiveData<List<Game>>() // Utilisation d'un MutableLiveData privé
    val games: LiveData<List<Game>> get() = _games // Exposer le LiveData

    init {
        // Charge initialement les jeux
        loadGames()
    }

    private fun loadGames() {
        // Appelle le repository pour récupérer la liste des jeux
        _games.value = gameRepository.getGames()
    }

    fun addGame(game: Game) {
        gameRepository.addGame(game)
        loadGames() // Recharge les jeux après ajout
    }
}
