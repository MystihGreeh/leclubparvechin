package com.example.leclubparvechin.view

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.leclubparvechin.R
import com.example.leclubparvechin.repository.CourseRepository
import com.example.leclubparvechin.viewmodel.CourseViewModel
import com.example.leclubparvechin.CourseViewModelFactory
import java.util.*

class NewCourseFragment : Fragment() {

    private lateinit var courseViewModel: CourseViewModel
    private lateinit var dateTextView: TextView
    private lateinit var timeTextView: TextView
    private var selectedDate: String = ""
    private var selectedTime: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_new_course, container, false)

        // Initialisation du CourseViewModel
        val courseRepository = CourseRepository()
        val factory = CourseViewModelFactory(courseRepository)
        courseViewModel = ViewModelProvider(this, factory).get(CourseViewModel::class.java)

        val teacherSpinner: Spinner = view.findViewById(R.id.teacherSpinner)
        val themeSpinner: Spinner = view.findViewById(R.id.themeSpinner)
        dateTextView = view.findViewById(R.id.dateTextView)
        timeTextView = view.findViewById(R.id.timeTextView)
        val addCourseButton: Button = view.findViewById(R.id.addCourseButton)
        val datePickerButton: Button = view.findViewById(R.id.datePickerButton)
        val timePickerButton: Button = view.findViewById(R.id.timePickerButton)

        // Configurez les options pour le Spinner du professeur
        val teachers = arrayOf("Charlotte", "Greg")
        val teacherAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, teachers)
        teacherAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        teacherSpinner.adapter = teacherAdapter

        // Configurez les options pour le Spinner du thème
        val themes = arrayOf("Bunker", "Chipping", "Putting", "Wedging", "Attaque de Green", "Driving", "Autre")
        val themeAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, themes)
        themeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        themeSpinner.adapter = themeAdapter

        // DatePicker
        datePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear" // Format JJ/MM/AAAA
                dateTextView.text = selectedDate
            }, year, month, day)

            datePickerDialog.show()
        }

        // TimePicker
        timePickerButton.setOnClickListener {
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
                selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute) // Format HH:MM
                timeTextView.text = selectedTime
            }, hour, minute, true)

            timePickerDialog.show()
        }

        addCourseButton.setOnClickListener {
            val teacher = teacherSpinner.selectedItem.toString()
            val theme = themeSpinner.selectedItem.toString()

            if (teacher.isNotEmpty() && theme.isNotEmpty() && selectedDate.isNotEmpty() && selectedTime.isNotEmpty()) {
                // Ajouter le cours
                courseViewModel.addCourse(teacher, theme, selectedDate, selectedTime)
                Toast.makeText(requireContext(), "Cours ajouté!", Toast.LENGTH_SHORT).show()
                // Retour à la liste des cours
                requireActivity().onBackPressed()
            } else {
                Toast.makeText(requireContext(), "Veuillez remplir tous les champs!", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }
}
