package com.calyrsoft.ucbp1.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.calyrsoft.ucbp1.features.dollar.data.database.dao.IDollarDao
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity

// Define la base de datos y su versión.
@Database(
    entities = [DollarEntity::class],
    version = 3, // La versión actual de la base de datos
    exportSchema = false
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun dollarDao(): IDollarDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null

        // Define la migración para actualizar la base de datos de la versión 2 a la 3.
        // Ahora solo añade la columna 'dollar_usdc'.
        val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE dollars ADD COLUMN dollar_usdc TEXT")
            }
        }

        fun getDatabase(context: Context): AppRoomDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "db_dollar"
                )
                    // Agrega la migración corregida.
                    .addMigrations(MIGRATION_1_2)
                    .build()
                    .also { Instance = it }
            }
        }
    }
}