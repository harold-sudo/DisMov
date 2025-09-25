package com.calyrsoft.ucbp1.features.dollar.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "dollars")
data class DollarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "dollar_official")
    var dollarOfficial: String? = null,

    @ColumnInfo(name = "dollar_paralelo")
    var dollarParalelo: String? = null,

    @ColumnInfo(name = "dollar_USDT")
    var dollarUSDT: String? = null,

    @ColumnInfo(name = "dollar_USDC")
    var dollarUSDC: String? = null,

    @ColumnInfo(name = "timestamp")
    var timestamp: Long = 0)

