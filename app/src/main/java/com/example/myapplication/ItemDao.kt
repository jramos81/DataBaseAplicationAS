package com.example.myapplication

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


// Interfaz DAO para acceder a la base de datos
@Dao
interface ItemDao {
    // Método para insertar un nuevo elemento en la base de datos
    @Insert
    fun insertItem(item: Item): Long
    // Método para obtener todos los elementos de la base de datos
    @Query("SELECT * FROM items")
    fun getAllItems(): List<Item>
}

/*Explicación del código:
1.	Paquete e importaciones:
    package com.example.myapplication: Define el paquete del proyecto.
    Las importaciones incluyen las anotaciones de Room (@Dao, @Insert, @Query)
2.	Interfaz ItemDao:
    ItemDao es una interfaz que define los métodos para acceder a la base de datos.
    Está anotada con @Dao, lo que indica que es un Data Access Object (DAO) para Room.
3.	Métodos DAO:
    insertItem:
        Anotado con @Insert, este método se utiliza para insertar un nuevo elemento (Item) en la base de datos.
        Devuelve un Long que representa el ID del elemento insertado.
    getAllItems:
    Anotado con @Query, este método se utiliza para obtener todos los elementos de la tabla items.
    La consulta SQL SELECT * FROM items selecciona todos los registros de la tabla items.
    Devuelve una lista de elementos (List<Item>).
Este DAO proporciona las operaciones básicas para insertar y recuperar elementos de la base de datos.

 */
