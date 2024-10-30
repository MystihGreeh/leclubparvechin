import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.leclubparvechin.model.course.Course
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class CourseRepository {

    private val auth = FirebaseAuth.getInstance()
    private val db = FirebaseFirestore.getInstance() // Instance de Firestore
    private val _courses = MutableLiveData<List<Course>>()
    val courses: LiveData<List<Course>> get() = _courses

    // Méthode pour récupérer les cours avec des paramètres (pour le ViewModel)
    fun fetchCoursesWithParams(currentDateTime: String, userId: String): Task<QuerySnapshot> {
        return db.collection("courses")
            .whereEqualTo("createdBy", userId)  // Filtrer par utilisateur actif
            .whereGreaterThan("date", currentDateTime) // Filtrer pour les cours futurs
            .orderBy("date", Query.Direction.ASCENDING) // Trier les cours par date
            .get()
    }

    // Nouvelle méthode pour obtenir les cours en tant que LiveData
    fun fetchCoursesLiveData(): LiveData<List<Course>> {
        val liveDataCourses = MutableLiveData<List<Course>>()
        val currentDateTime = getCurrentDateTime()
        val userId = FirebaseAuth.getInstance().currentUser?.uid // Récupérer l'UID de l'utilisateur connecté

        if (userId != null) { // Assurez-vous que l'utilisateur est connecté
            db.collection("courses")
                .whereEqualTo("createdBy", userId) // Filtrer par utilisateur actif
                .whereGreaterThan("date", currentDateTime) // Filtrer pour les cours futurs
                .orderBy("date", Query.Direction.ASCENDING) // Trier les cours par date
                .get()
                .addOnSuccessListener { result ->
                    val coursesList = result.toObjects(Course::class.java)
                    liveDataCourses.value = coursesList // Met à jour la LiveData avec les résultats
                }
                .addOnFailureListener { exception ->
                    // Gérer l’erreur si nécessaire
                    exception.printStackTrace()
                }
        }

        return liveDataCourses
    }

    // Fonction pour ajouter un cours
    fun addCourse(course: Course): Task<Void> {
        val user = auth.currentUser
        val newCourseRef = db.collection("courses").document()

        // Création d'une copie du cours avec un identifiant unique et l'utilisateur
        val courseWithId = course.copy(id = newCourseRef.id, createdBy = user?.uid ?: "")

        return newCourseRef.set(courseWithId)
    }

    // Utilitaire pour obtenir la date actuelle au format requis
    fun getCurrentDateTime(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return dateFormat.format(Date())
    }
}
