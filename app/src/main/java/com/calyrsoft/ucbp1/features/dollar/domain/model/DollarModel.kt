package com.calyrsoft.ucbp1.features.dollar.domain.model


data class DollarModel(
    var dollarOfficial: String? = null,
    var dollarParalelo: String? = null,
    var dollarUSDT: String? = null,
    var dollarUSDC: String? = null,
    val timestamp: Long = 0
)

