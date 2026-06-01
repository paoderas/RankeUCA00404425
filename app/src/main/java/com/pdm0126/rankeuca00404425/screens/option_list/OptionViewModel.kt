package com.pdm0126.rankeuca00404425.screens.option_list

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.pdm0126.rankeuca00404425.model.Option

@Composable
fun ProductListScreen(

    viewModel: OptionViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    LaunchedEffect(Unit) {
        viewModel.loadOptions()
    }
    Scaffold(
        topBar = {
            @OptIn(ExperimentalMaterial3Api::class)
            TopAppBar(title = { Text("Mejores lugares") })
        },
        modifier = modifier.fillMaxSize()
    ) { innerPadding ->


        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {


            when(val state = uiState) {
                is OptionState.Loading -> {
                    CircularProgressIndicator()
                }
                is OptionState.Success -> {
                    OptionsList(options = state.options)
                }
                is OptionState.Error -> {
                    ErrorWidget(
                        errorMessage = state.message,
                        onRetry = {viewModel.loadOptions()}
                    )
                }
            }
        }
    }
}


@Composable
fun OptionsList(options : List<Option>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)

    ) {
        items(options) {
                option ->
            Card(modifier = Modifier.fillMaxSize()) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(text = option.name, style = MaterialTheme.typography.titleMedium)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = option.votes.toString(), style = MaterialTheme.typography.bodySmall)
                }
            }

        }
    }
}

@Composable
fun ErrorWidget(errorMessage: String, onRetry: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = errorMessage, color = MaterialTheme.colorScheme.error)
        Spacer(modifier = Modifier.height((8.dp)))
        Button(onRetry) {
            Text("Reintentar carga")
        }
    }
}