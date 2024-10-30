package com.example.leclubparvechin.view

import CourseRepository
import CourseViewModel
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.leclubparvechin.R
import com.example.leclubparvechin.model.course.Course
import com.example.leclubparvechin.viewmodel.CourseViewModelFactory
import java.util.*

class NewCourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var dateTextView: TextView // Ajout de la déclaration pour dateTextView
    private lateinit var timeTextView: TextView // Ajout de la déclaration pour timeTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_new_course, container, false)

        // Initialisation du CourseViewModel
        val courseRepository = CourseRepository()
        courseViewModel = ViewModelProvider(this, CourseViewModelFactory(courseRepository)).get(CourseViewModel::class.java)

        // Récupération des éléments de la vue
        val teacherSpinner: Spinner = view.findViewById(R.id.teacherSpinner)
        val themeSpinner: Spinner = view.findViewById(R.id.themeSpinner)
        dateTextView = view.findViewById(R.id.dateTextView)
        timeTextView = view.findViewById(R.id.timeTextView)

        // Initialisation des Spinners avec les ressources
        val teachers = resources.getStringArray(R.array.teacher_names) // Récupération des noms des professeurs
        val themes = resources.getStringArray(R.array.courses_theme) // Récupération des thèmes

        // Création et affectation de l'adaptateur pour le Spinner des professeurs
        val teacherAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teachers)
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        teacherSpinner.adapter = teacherAdapter

        // Création et affectation de l'adaptateur pour le Spinner des thèmes
        val themeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themes)
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        themeSpinner.adapter = themeAdapter

        // Gérer le clic sur le bouton "Choisir la Date"
        val datePickerButton: Button = view.findViewById(R.id.newcourseDatePickerButton)
        datePickerButton.setOnClickListener {
            showDatePickerDialog() // Méthode pour afficher le sélecteur de date
        }

        // Gérer le clic sur le bouton "Choisir l'Heure"
        val timePickerButton: Button = view.findViewById(R.id.newCourseTimePickerButton)
        timePickerButton.setOnClickListener {
            showTimePickerDialog() // Méthode pour afficher le sélecteur d'heure
        }

        // Gérer le clic sur le bouton "Ajouter le Cours"
        val addCourseButton: Button = view.findViewById(R.id.addCourseButton)
        addCourseButton.setOnClickListener {
            // Créer l'objet Course
            val course = Course(
                teacher = teacherSpinner.selectedItem.toString(),
                theme = themeSpinner.selectedItem.toString(),
                date = dateTextView.text.toString(),
                time = timeTextView.text.toString()
            )

            // Ajouter le cours au ViewModel
            courseViewModel.addCourse(course)

            // Naviguer vers la liste des cours
            view.findNavController().navigate(R.id.action_newCourseFragment_to_courseListFragment)
        }

        return view
    }

    // Méthode pour afficher le sélecteur de date
    private fun showDatePickerDialog() {
        // Récupérer la date actuelle
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        // Créer et afficher le DatePickerDialog
        DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            // Mettre à jour le TextView avec la date choisie
            dateTextView.text = String.format("%02d/%02d/%d", selectedDay, selectedMonth + 1, selectedYear)
        }, year, month, day).show()
    }

    // Méthode pour afficher le sélecteur d'heure
    private fun showTimePickerDialog() {
        // Récupérer l'heure actuelle
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        // Créer et afficher le TimePickerDialog
        TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            // Mettre à jour le TextView avec l'heure choisie
            timeTextView.text = String.format("%02d:%02d", selectedHour, selectedMinute)
        }, hour, minute, true).show()
    }
}
