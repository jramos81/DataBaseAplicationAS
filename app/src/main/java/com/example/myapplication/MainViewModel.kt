package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel(private val dao: ItemDao) : ViewModel() {
    // Estado mutable que contiene la lista de elementos
    private val _items = MutableStateFlow<List<Item>>(emptyList())
    // Estado inmutable que expone la lista de elementos
    val items: StateFlow<List<Item>> = _items

    init {
        // Inicializa la carga de elementos al crear el ViewModel
        fetchItems()
    }

    private fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {

            _items.value = dao.getAllItems()
        }
    }
    // Función para agregar un nuevo elemento a la base de datos
    fun addItem(name: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dao.insertItem(Item(name = name))
            // Vuelve a cargar los elementos después de insertar uno nuevo
            fetchItems()
        }
    }
}
/*
Explicación del código:
1.	Clase MainViewModel:
    MainViewModel extiende ViewModel y recibe un ItemDao como parámetro.
    ItemDao es el objeto de acceso a datos (DAO) que maneja las operaciones de la base de datos.
2.	Propiedades:
    _items: Un MutableStateFlow que contiene una lista mutable de elementos (List<Item>). Inicialmente, está vacío.
    items: Un StateFlow inmutable que expone la lista de elementos. Esto permite que otras partes de la aplicación observen los cambios en la lista sin poder modificarla directamente.
3.	Inicialización:
    En el bloque init, se llama a fetchItems() para cargar los elementos desde la base de datos cuando se crea el ViewModel.
4.	Función fetchItems:
    fetchItems es una función privada que obtiene los elementos desde la base de datos.
    Utiliza viewModelScope.launch para ejecutar la operación en un hilo de entrada/salida (IO) para no bloquear el hilo principal.
    dao.getAllItems() obtiene todos los elementos de la base de datos y actualiza _items.value con la lista obtenida.
5.	Función addItem:
    addItem es una función pública que agrega un nuevo elemento a la base de datos.
    Recibe el nombre del nuevo elemento como parámetro.
    Utiliza viewModelScope.launch para insertar el nuevo elemento en la base de datos en un hilo de entrada/salida (IO).
    Después de insertar el nuevo elemento, llama a fetchItems() para recargar la lista de elementos.
Este ViewModel gestiona la lógica de negocio de la aplicación, incluyendo la carga y adición de elementos a la base de datos.
 */