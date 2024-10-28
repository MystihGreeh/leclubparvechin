package com.example.leclubparvechin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leclubparvechin.repository.CourseRepository
import com.example.yourapp.Course
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    // Liste des cours
    val courses: LiveData<List<Course>> = courseRepository.fetchCourses() // Observez les changements directement depuis le repository

    init {
        // Charger les cours au démarrage (dans ce cas, c'est déjà géré par l'init du repository)
    }

    fun addCourse(teacher: String, theme: String, date: String, time: String) {
        // Ajout d'un nouveau cours
        val newCourse = Course(teacher, theme, date, time)

        viewModelScope.launch {
            courseRepository.addCourse(newCourse) // Ajoute le cours dans le repository
            // Pas besoin de recharger les cours, car LiveData sera mis à jour automatiquement
        }
    }
}
