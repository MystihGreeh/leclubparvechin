package com.example.leclubparvechin.viewmodel

import CourseRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leclubparvechin.model.course.Course
import kotlinx.coroutines.launch

class CourseListViewModel(private val repository: CourseRepository) : ViewModel() {

    // LiveData pour observer la liste des cours
    val courses: LiveData<List<Course>> = repository.fetchCoursesLiveData()

    // Méthode pour ajouter un nouveau cours
    fun addCourse(course: Course) {
        viewModelScope.launch {
            repository.addCourse(course)
        }
    }
}
