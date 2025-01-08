package com.example.myapplication

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Item::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    // Método abstracto para obtener el DAO
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        // Método para obtener la instancia de la base de datos
        fun getDatabase(context: Context): AppDatabase {
            // Si INSTANCE es null, crear una nueva instancia de la base de datos
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "item_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
/*
Explicación del código:
1.	Paquete e importaciones:
    import android.content.Context: Importa el contexto de la aplicación.
    import androidx.room.Database: Importa la anotación Database de Room.
    import androidx.room.Room: Importa la clase Room para crear la base de datos.
    import androidx.room.RoomDatabase: Importa la clase base RoomDatabase.
2.	Anotación @Database:
    @Database(entities = [Item::class], version = 1, exportSchema = false): Define la base de datos de Room.
        entities = [Item::class]: Especifica que la entidad Item está incluida en la base de datos.
        version = 1: Especifica la versión de la base de datos.
        exportSchema = false: Indica que no se exportará el esquema de la base de datos.
3.	Clase AppDatabase:
    abstract class AppDatabase : RoomDatabase(): Define una clase abstracta que extiende RoomDatabase.
4.	Método abstracto itemDao:
    abstract fun itemDao(): ItemDao: Método abstracto para obtener el DAO (ItemDao).
5.	Objeto companion:
    companion object: Define un objeto estático para contener la instancia de la base de datos.
    @Volatile private var INSTANCE: AppDatabase? = null: Declara una variable INSTANCE para almacenar la instancia de la base de datos. @Volatile asegura que los cambios a esta variable sean visibles para todos los hilos.
6.	Método getDatabase:
    fun getDatabase(context: Context): AppDatabase: Método para obtener la instancia de la base de datos.
    return INSTANCE ?: synchronized(this): Si INSTANCE es null, sincroniza este bloque para evitar la creación de múltiples instancias en hilos concurrentes.
    val instance = Room.databaseBuilder(...): Crea una nueva instancia de la base de datos utilizando Room.databaseBuilder.
    INSTANCE = instance: Asigna la nueva instancia a INSTANCE.
    instance: Devuelve la instancia de la base de datos.
Este código configura la base de datos de Room, asegurando que solo haya una única instancia de la base de datos en toda la aplicación.
 */