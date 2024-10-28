package com.example.leclubparvechin.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.leclubparvechin.R
import com.example.yourapp.Course

class CourseAdapter : RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    private var courses: List<Course> = emptyList()

    class CourseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val courseDate: TextView = itemView.findViewById(R.id.course_date)
        private val courseTime: TextView = itemView.findViewById(R.id.course_time)
        private val courseProfessor: TextView = itemView.findViewById(R.id.course_professor)
        private val courseTheme: TextView = itemView.findViewById(R.id.course_theme)

        fun bind(course: Course) {
            courseDate.text = course.date
            courseTime.text = course.time
            courseProfessor.text = course.teacher
            courseTheme.text = course.theme
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_course, parent, false)
        return CourseViewHolder(view)
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val currentCourse = courses[position]
        holder.bind(currentCourse)
    }

    override fun getItemCount(): Int = courses.size

    fun setCourses(courses: List<Course>) {
        this.courses = courses
        notifyDataSetChanged() // Notifie l'adaptateur que les données ont changé
    }
}
