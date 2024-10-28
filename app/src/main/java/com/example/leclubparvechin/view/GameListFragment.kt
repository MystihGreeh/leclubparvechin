package com.example.leclubparvechin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leclubparvechin.R
import com.example.leclubparvechin.adapters.GameAdapter
import com.example.leclubparvechin.repository.GameRepository
import com.example.leclubparvechin.viewmodel.GameViewModel
import com.example.leclubparvechin.viewmodel.GameViewModelFactory
import com.google.android.material.floatingactionbutton.FloatingActionButton

class GameListFragment : Fragment() {

    //Affiche la liste des parties précédentes pour le mode et le terrain sélectionnés

    private lateinit var gameViewModel: GameViewModel
    private lateinit var gameAdapter: GameAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_game_list, container, false)

        // Initialisation du GameViewModel
        val gameRepository = GameRepository() // Assurez-vous que votre GameRepository est accessible ici
        val factory = GameViewModelFactory(gameRepository)
        gameViewModel = ViewModelProvider(this, factory).get(GameViewModel::class.java)



        // Configuration de la RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerview)
        gameAdapter = GameAdapter()
        recyclerView.adapter = gameAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observer les jeux
        gameViewModel.games.observe(viewLifecycleOwner) { games ->
            gameAdapter.setGames(games)
        }

        // Gérer le clic sur le bouton "Nouvelle Partie"
        val addGameButton: FloatingActionButton = view.findViewById(R.id.fab_add_game)
        addGameButton.setOnClickListener {
            findNavController().navigate(R.id.action_gameListFragment_to_newGameFragment)


    }

        return view
    }
}