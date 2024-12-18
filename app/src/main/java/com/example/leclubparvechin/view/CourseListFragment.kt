package com.example.leclubparvechin.view

import CourseRepository
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.leclubparvechin.R
import com.example.leclubparvechin.adapters.CourseAdapter
import com.example.leclubparvechin.viewmodel.CourseListViewModel
import com.example.leclubparvechin.viewmodel.CourseListViewModelFactory
import com.example.leclubparvechin.model.course.Course // Assurez-vous d'importer la bonne classe Course
import com.google.android.material.floatingactionbutton.FloatingActionButton

class CourseListFragment : Fragment() {

    private lateinit var courseViewModel: CourseListViewModel
    private lateinit var courseAdapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_course_list, container, false)

        // Initialisation du CourseRepository et du ViewModel
        val courseRepository = CourseRepository()
        val factory = CourseListViewModelFactory(courseRepository)
        courseViewModel = ViewModelProvider(this, factory).get(CourseListViewModel::class.java)

        // Configuration de la RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recycler_view_courses)
        courseAdapter = CourseAdapter()
        recyclerView.adapter = courseAdapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        // Observer les cours
        courseViewModel.courses.observe(viewLifecycleOwner) { courses ->
            courseAdapter.setCourses(courses) // Assurez-vous que "courses" est de type List<Course>
        }

        // Gérer le clic sur le bouton "Ajouter un cours"
        val addCourseButton: FloatingActionButton = view.findViewById(R.id.fab_add_course)
        addCourseButton.setOnClickListener {
            findNavController().navigate(R.id.action_courseListFragment_to_newCourseFragment)
        }

        return view
    }
}
