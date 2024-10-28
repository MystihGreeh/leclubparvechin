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
        val view = inflater.inflate(R.layout.fragment_new_game, container, false)

        // Initialisation des listes de joueurs
        playerNames = resources.getStringArray(R.array.player_names).toList()
        playerScores = mutableListOf()

        // Initialisation du GameViewModel
        val gameRepository = GameRepository()
        gameViewModel = ViewModelProvider(this, GameViewModelFactory(gameRepository)).get(GameViewModel::class.java)

        // Configuration des menus déroulants et du tableau de scores
        val gameTypeSpinner: Spinner = view.findViewById(R.id.spinner_game_type)
        val terrainSpinner: Spinner = view.findViewById(R.id.spinner_terrain)

        // Conversion des valeurs du Spinner en GameType et Terrain
        val selectedGameTypeString = gameTypeSpinner.selectedItem.toString()
        val selectedTerrainString = terrainSpinner.selectedItem.toString()

        // Convertir les String en GameType et Terrain
        val selectedGameType = try {
            GameType.valueOf(selectedGameTypeString)
        } catch (e: IllegalArgumentException) {
            GameType.DEFAULT // Utilisez une valeur par défaut ou gérez l'exception
        }

        val selectedTerrain = try {
            Terrain.valueOf(selectedTerrainString)
        } catch (e: IllegalArgumentException) {
            Terrain.DEFAULT // Utilisez une valeur par défaut ou gérez l'exception
        }

        // Gérer le clic sur le bouton "Valider"
        val validateButton: Button = view.findViewById(R.id.btn_validate)
        validateButton.setOnClickListener {
            // Créer l'objet Game
            val game = Game(
                id = System.currentTimeMillis().toString(),
                type = selectedGameType,
                terrain = selectedTerrain,
                playersScores = playerScores,
                comment = "Test",
                date = Date().time
            )
            gameViewModel.addGame(game)
            view.findNavController().navigate(R.id.action_newGameFragment_to_gameListFragment)
        }

        return view
    }
}
