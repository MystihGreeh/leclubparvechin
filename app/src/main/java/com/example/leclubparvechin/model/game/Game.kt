package com.example.leclubparvechin.model.game

data class Game(
    val id: String, // ID unique pour la partie
    val type: GameType, // Utilise le modèle GameType
    val terrain: Terrain, // Utilise le modèle Terrain
    val playersScores: List<PlayerScore>, // Liste pour stocker les scores des joueurs
    val comment: String, // Commentaire pour la partie
    val date: Long // Timestamp pour la date de jeu
)





