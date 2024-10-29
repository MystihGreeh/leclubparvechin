package com.example.leclubparvechin.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.leclubparvechin.R
import com.example.leclubparvechin.repository.GameRepository
import com.example.leclubparvechin.viewmodel.GameViewModel
import com.example.leclubparvechin.viewmodel.GameViewModelFactory
import android.view.View
import android.widget.ImageView
import androidx.navigation.NavController

class MainActivity : AppCompatActivity() {
    private val gameViewModel: GameViewModel by viewModels {
        GameViewModelFactory(GameRepository())
    }

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_main_activity) // Assurez-vous que le nom du layout est correct.

        // Configuration de la Toolbar en tant qu'ActionBar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout) // Récupération du DrawerLayout

        // Récupérer le NavHostFragment et le NavController associé
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        // Configurer l'ActionBar avec le NavController
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        // Afficher l'icône de navigation (burger menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)

        // Ouvrir le Drawer lorsque le burger menu est cliqué
        toolbar.setNavigationOnClickListener {
            drawerLayout.open()
        }

        // Écouteurs de clic pour les éléments du Drawer Menu
        setupDrawerMenu(navController)
    }

    private fun setupDrawerMenu(navController: NavController) {
        // Récupération des ImageView pour les boutons du drawer
        val jeuxButton: ImageView = findViewById(R.id.jeux_button)
        val coursButton: ImageView = findViewById(R.id.cours_button)
        val calendrierButton: ImageView = findViewById(R.id.calendrier_button)
        val settingsButton: ImageView = findViewById(R.id.settings_button)

        // Naviguer vers le fragment CourseList lorsque l'utilisateur clique sur l'icône cours
        coursButton.setOnClickListener {
            navController.navigate(R.id.courseListFragment) // Remplacez "coursListFragment" par l'ID réel de votre fragment dans le nav_graph
            drawerLayout.close() // Ferme le drawer après la sélection
        }

        // Écoutez les autres boutons selon vos besoins
        jeuxButton.setOnClickListener {
            navController.navigate(R.id.gameListFragment) // Exemple pour l'icône jeux
            drawerLayout.close()
        }

        calendrierButton.setOnClickListener {
            navController.navigate(R.id.calendrier_button) // Exemple pour l'icône calendrier
            drawerLayout.close()
        }

        settingsButton.setOnClickListener {
            navController.navigate(R.id.settings_button) // Exemple pour l'icône paramètres
            drawerLayout.close()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        // Utiliser le NavController pour la navigation en arrière
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragment_container) as NavHostFragment
        return navHostFragment.navController.navigateUp() || super.onSupportNavigateUp()
    }
}
