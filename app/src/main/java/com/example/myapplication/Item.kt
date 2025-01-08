package com.example.myapplication

import androidx.room.Entity
import androidx.room.PrimaryKey

// Definición de la entidad de la base de datos
@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    val name: String
)
/*
Explicación del código:
1.	Paquete e importaciones:
    package com.example.myapplication: Define el paquete del proyecto.
    Las importaciones incluyen las anotaciones de Room (@Entity, @PrimaryKey).
2.	Entidad Item:
    @Entity(tableName = "items"): Anotación que indica que esta clase representa una tabla en la base de datos llamada items.
    data class Item: Define una clase de datos llamada Item.
3.	Propiedades de la entidad:
    @PrimaryKey(autoGenerate = true) val id: Int = 0:
        @PrimaryKey: Anotación que indica que esta propiedad es la clave primaria de la tabla.
        autoGenerate = true: Indica que el valor de id se generará automáticamente.
        val id: Int = 0: Define la propiedad id como un entero con un valor predeterminado de 0.
    val name: String: Define la propiedad name como una cadena de texto (String).
Esta clase Item representa una entidad en la base de datos con dos propiedades: id (clave primaria) y name (nombre del elemento).
 */