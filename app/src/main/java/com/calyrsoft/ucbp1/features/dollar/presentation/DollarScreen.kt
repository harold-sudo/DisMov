package com.calyrsoft.ucbp1.features.dollar.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import com.calyrsoft.ucbp1.features.dollar.domain.model.DollarModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.AttachMoney

@Composable
fun DollarScreen(viewModelDollar: DollarViewModel = koinViewModel()) {
    val state = viewModelDollar.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        when (val stateValue = state.value) {
            is DollarViewModel.DollarUIState.Error -> Text(stateValue.message)
            DollarViewModel.DollarUIState.Loading -> CircularProgressIndicator()
            is DollarViewModel.DollarUIState.Success -> {
                val data = stateValue.data

                Text(
                    text = "Última actualización: ${formatTimestamp(data.timestamp)}",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodySmall
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    contentPadding = PaddingValues(16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { DollarRateCard("Oficial", data.dollarOfficial) }
                    item { DollarRateCard("Paralelo", data.dollarParalelo) }
                    item { DollarRateCard("USDT", data.dollarUSDT) }
                    item { DollarRateCard("USDC", data.dollarUSDC) }
                }
            }
        }
    }
}

@Composable
fun DollarRateCard(title: String, rate: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
//            Icon(
//                imageVector = Icons.Default.AttachMoney,
//                contentDescription = title,
//                modifier = Modifier.size(48.dp)
//            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "${rate ?: "N/A"} BS",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

private fun formatTimestamp(timestamp: Long): String {
    val date = Date(timestamp)
    val formatter = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    return formatter.format(date)
}