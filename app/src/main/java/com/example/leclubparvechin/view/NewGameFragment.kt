package com.example.leclubparvechin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.leclubparvechin.R
import com.example.leclubparvechin.model.game.Game
import com.example.leclubparvechin.model.game.GameType
import com.example.leclubparvechin.model.game.PlayerScore
import com.example.leclubparvechin.model.game.Terrain
import com.example.leclubparvechin.repository.GameRepository
import com.example.leclubparvechin.viewmodel.GameViewModel
import com.example.leclubparvechin.viewmodel.GameViewModelFactory
import java.util.Date

class NewGameFragment : Fragment() {

    private lateinit var gameViewModel: GameViewModel
    private lateinit var playerNames: List<String>
    private lateinit var playerScores: List<PlayerScore>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_current_hole, container, false)

        // Initialisation des listes de joueurs
        playerNames = resources.getStringArray(R.array.player_names).toList()
        playerScores = mutableListOf()

        // Initialisation du GameViewModel
        val gameRepository = GameRepository()
        gameViewModel = ViewModelProvider(this, GameViewModelFactory(gameRepository)).get(GameViewModel::class.java)

        return view
    }
}
