package com.calyrsoft.ucbp1.features.dollar.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.calyrsoft.ucbp1.features.dollar.data.database.dao.IDollarDao
import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity

@Database(
    entities = [DollarEntity::class],
    version = 2,
    exportSchema = false // Recommendation 1: Explicitly set schema export
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun dollarDao(): IDollarDao

    companion object {
        @Volatile
        private var Instance: AppRoomDatabase? = null

        fun getDatabase(context: Context): AppRoomDatabase {
            return Instance ?: synchronized(this) {
                // Recommendation 2: Use applicationContext
                Room.databaseBuilder(
                    context.applicationContext,
                    AppRoomDatabase::class.java,
                    "dollar_db"
                )
                    // Recommendation 3 (Optional - for development):
                    // If you change the schema and version, this will recreate the database
                    // instead of crashing. Remove for production if data loss is not acceptable.
                    // .fallbackToDestructiveMigration()
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
