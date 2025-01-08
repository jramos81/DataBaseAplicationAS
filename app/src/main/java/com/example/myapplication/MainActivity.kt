package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

// Clase principal de la actividad
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Sortu datu-basea eta ViewModel
        val database = AppDatabase.getDatabase(applicationContext)
        val dao = database.itemDao()
        val viewModel = MainViewModel(dao)
        // Establecer el contenido de la actividad
        setContent {
            MyApp(viewModel)
        }
    }

}


@Composable
fun MyApp(viewModel: MainViewModel) {
    // Obtener los elementos desde el ViewModel
    val items by viewModel.items.collectAsState()

    var text by remember { mutableStateOf(TextFieldValue("")) }

    Column(Modifier.padding(16.dp)) {
        // Testu sarrera
        BasicTextField(
            value = text,
            onValueChange = { text = it },
            modifier = Modifier.fillMaxWidth().padding(8.dp)
        )

        // Gehitzeko botoia
        Button(onClick = {
            if (text.text.isNotEmpty()) {
                viewModel.addItem(text.text)
                text = TextFieldValue("")
            }
        }) {
            Text("Gorde")
        }

        Spacer(Modifier.height(16.dp))

        // Gordetako elementuak erakusteko zerrenda
        items.forEach { item ->
            Text("Item: ${item.name}", Modifier.padding(8.dp))
        }
    }
}
/*
Explicación del código:
1.	Paquete e importaciones:
    package com.example.myapplication: Define el paquete del proyecto.
    Las importaciones incluyen varias bibliotecas de Android y Jetpack Compose necesarias para la interfaz de usuario y la gestión de estados.
2.	Clase MainActivity:
    MainActivity extiende ComponentActivity y es la actividad principal de la aplicación.
    En el método onCreate, se inicializa la base de datos (AppDatabase) y se obtiene el DAO (itemDao).
    Se crea una instancia de MainViewModel con el DAO.
    setContent establece el contenido de la actividad utilizando la función composable MyApp.
3.	Función composable MyApp:
    MyApp recibe un viewModel como parámetro.
    Utiliza collectAsState para observar los elementos del viewModel.
    Define un estado mutable text para el campo de texto.
    La interfaz de usuario se organiza en una columna (Column) con un padding de 16dp.
    Incluye un BasicTextField para la entrada de texto.
    Un botón (Button) que, al hacer clic, agrega el texto ingresado a la lista de elementos a través del viewModel y luego limpia el campo de texto.
    Un Spacer para agregar espacio vertical.
    Un bucle forEach para mostrar cada elemento guardado en la lista.
Este programa es una aplicación simple que permite al usuario ingresar texto, guardarlo en una base de datos y mostrar los elementos guardados en una lista.
 */
