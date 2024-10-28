package com.example.leclubparvechin.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.yourapp.Course

class CourseRepository {

    private val courseList = mutableListOf<Course>()

    // MutableLiveData pour émettre la liste des cours
    private val _courses = MutableLiveData<List<Course>>(courseList)
    val courses: LiveData<List<Course>> get() = _courses

    // Simule une base de données ou un service
    init {
        // Ajout de quelques cours par défaut
        courseList.add(Course("Charlotte", "Bunker", "2024-10-29", "10:00"))
        courseList.add(Course("Greg", "Driving", "2024-10-30", "14:00"))
        // Émettez la liste initiale
        _courses.value = courseList
    }

    // Récupérer tous les cours
    fun fetchCourses(): LiveData<List<Course>> {
        return courses // Retourne LiveData contenant la liste des cours
    }

    // Ajouter un nouveau cours
    suspend fun addCourse(course: Course) {
        // Ajoutez le cours à la liste
        courseList.add(course)
        // Émettez la nouvelle liste des cours
        _courses.value = courseList
    }
}
