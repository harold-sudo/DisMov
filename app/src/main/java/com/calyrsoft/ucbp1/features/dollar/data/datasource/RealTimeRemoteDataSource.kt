package com.calyrsoft.ucbp1.features.dollar.data.datasource

import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class RealTimeRemoteDataSource {

    suspend fun getDollarUpdates(): Flow<DollarModel> = callbackFlow {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("dollar")

        val callback = object : ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val value = p0.getValue(DollarModel::class.java)
                if (value != null) {
                    trySend(value)
                }
            }

            // **Se corrige el manejo de errores**
            override fun onCancelled(error: DatabaseError) {
                close(error.toException())
            }
        }

        myRef.addValueEventListener(callback)

        awaitClose {
            myRef.removeEventListener(callback)
        }
    }
}