package com.example.leclubparvechin.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.leclubparvechin.R
import com.example.leclubparvechin.model.game.Game

class GameAdapter : RecyclerView.Adapter<GameAdapter.GameViewHolder>() {
    private var games = emptyList<Game>()

    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Liens des éléments de la vue, par exemple :
        // val gameName: TextView = itemView.findViewById(R.id.gameName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_game, parent, false)
        return GameViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        // Lier les données du jeu à la vue
        val currentGame = games[position]
        // Utilisez holder pour définir les valeurs des éléments de la vue
    }

    override fun getItemCount() = games.size

    fun setGames(games: List<Game>) {
        this.games = games
        notifyDataSetChanged()
    }
}