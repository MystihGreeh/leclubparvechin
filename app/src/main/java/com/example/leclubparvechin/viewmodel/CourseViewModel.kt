import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.leclubparvechin.model.course.Course
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.launch

class CourseViewModel(private val courseRepository: CourseRepository) : ViewModel() {

    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    init {
        fetchUpcomingCourses() // Charger les cours à la création du ViewModel
    }

    private fun fetchUpcomingCourses() {
        val currentDateTime = courseRepository.getCurrentDateTime()
        val userId = FirebaseAuth.getInstance().currentUser?.uid // Récupérer l'UID de l'utilisateur connecté

        if (userId != null) { // Assurez-vous que l'utilisateur est connecté
            viewModelScope.launch {
                // Utiliser la méthode avec paramètres pour récupérer les cours
                courseRepository.fetchCoursesWithParams(currentDateTime, userId).addOnSuccessListener { result: QuerySnapshot ->
                    val coursesList = result.toObjects(Course::class.java)
                    _courses.value = coursesList // Met à jour la LiveData
                }.addOnFailureListener { exception ->
                    // Gérer l’erreur si nécessaire
                    exception.printStackTrace()
                }
            }
        }
    }

    fun addCourse(course: Course) {
        viewModelScope.launch {
            courseRepository.addCourse(course) // Appel à la méthode d'ajout de cours
        }
    }
}
