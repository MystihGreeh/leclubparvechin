package com.example.leclubparvechin.repository

import com.example.leclubparvechin.model.game.Game

class GameRepository {

    //Simule ou gère la persistance des parties jouées

    private val games = mutableListOf<Game>()

    fun addGame(game: Game) {
        games.add(game)
    }

    fun getGames(): List<Game> {
        return games
    }

}