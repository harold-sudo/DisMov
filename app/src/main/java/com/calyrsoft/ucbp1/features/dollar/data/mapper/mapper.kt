package com.calyrsoft.ucbp1.features.dollar.data.mapper

import com.calyrsoft.ucbp1.features.dollar.data.database.entity.DollarEntity
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel

fun DollarEntity.toModel() : DollarModel {
    return DollarModel(
        dollarOfficial = dollarOfficial,
        dollarParalelo = dollarParalelo,
        dollarUSDT = dollarUSDT,
        dollarUSDC = dollarUSDC
    )
}

fun DollarModel.toEntity() : DollarEntity {
    return DollarEntity(
        dollarOfficial = dollarOfficial,
        dollarParalelo = dollarParalelo,
        dollarUSDT = dollarUSDT,
        dollarUSDC = dollarUSDC
    )
}