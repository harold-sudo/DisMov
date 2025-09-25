package com.calyrsoft.ucbp1.features.dollar.data.repository

import com.calyrsoft.ucbp1.features.dollar.data.datasource.DollarLocalDataSource
import com.calyrsoft.ucbp1.features.dollar.data.datasource.RealTimeRemoteDataSource
import com.calyrsoft.ucbp1.features.dollar.data.mapper.toEntity
import com.calyrsoft.ucbp1.features.dollar.data.mapper.toModel
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import com.calyrsoft.ucbp1.features.dollar.domain.repository.IDollarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DollarRepository(
    private val realTimeRemoteDataSource: RealTimeRemoteDataSource,
    private val localDataSource: DollarLocalDataSource
) : IDollarRepository {

    override suspend fun getDollar(): Flow<DollarModel> = flow {
        // Primero, emite el último valor de la base de datos local
        val localData = localDataSource.getLatestDollar()
        if (localData != null) {
            emit(localData)
        }

        // Luego, emite y guarda las actualizaciones de la fuente remota
        val remoteFlow = realTimeRemoteDataSource.getDollarUpdates()
            .onEach { dollarModel ->
                localDataSource.insert(dollarModel) // Guarda cada nueva actualización
            }

        // Combina el flujo local con el remoto
        emitAll(remoteFlow)
    }
}