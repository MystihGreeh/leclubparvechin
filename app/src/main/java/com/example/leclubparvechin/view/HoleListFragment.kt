package com.example.leclubparvechin.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.leclubparvechin.R

class HoleListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_hole_list, container, false)

        // Associer chaque CardView à un clic
        val cardHole1 = view.findViewById<CardView>(R.id.card_hole_1)
        val cardHole2 = view.findViewById<CardView>(R.id.card_hole_2)
        val cardHole3 = view.findViewById<CardView>(R.id.card_hole_3)
        val cardHole4 = view.findViewById<CardView>(R.id.card_hole_4)
        val cardHole5 = view.findViewById<CardView>(R.id.card_hole_5)
        val cardHole6 = view.findViewById<CardView>(R.id.card_hole_6)
        val cardHole7 = view.findViewById<CardView>(R.id.card_hole_7)
        val cardHole8 = view.findViewById<CardView>(R.id.card_hole_8)
        // Répéter pour tous les trous jusqu'à 18

        cardHole1.setOnClickListener {
            navigateToNewGameFragment(1)
        }

        cardHole2.setOnClickListener {
            navigateToNewGameFragment(2)
        }

        cardHole3.setOnClickListener {
            navigateToNewGameFragment(3)
        }

        cardHole4.setOnClickListener {
            navigateToNewGameFragment(4)
        }

        // Répéter pour tous les trous jusqu'à 18

        return view
    }

    private fun navigateToNewGameFragment(holeNumber: Int) {
        // Utilise la classe générée HoleListFragmentDirections
        val action = HoleListFragmentDirections.actionHoleListFragmentToNewGameFragment(holeNumber)
        findNavController().navigate(action)
    }
}
