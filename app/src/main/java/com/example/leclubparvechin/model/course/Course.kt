
package com.example.leclubparvechin.model.course

data class Course(
    val id: String = "",
    val createdBy: String = "",
    val teacher: String,
    val theme: String,
    val date: String,
    val time: String

) {
    // Constructeur sans argument, Kotlin gère cela automatiquement avec les valeurs par défaut.
    constructor() : this("", "", "", "", "", "")
}