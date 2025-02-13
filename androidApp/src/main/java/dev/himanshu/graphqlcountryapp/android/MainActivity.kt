package dev.himanshu.graphqlcountryapp.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import dev.himanshu.graphqlcountryapp.GetCountriesQuery
import dev.himanshu.graphqlcountryapp.viewModel.CountriesViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                val viewModel = koinViewModel<CountriesViewModel>()
                Surface(
                    modifier = Modifier
                        .safeContentPadding()
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                    if (uiState.isLoading) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    if (uiState.error.isNotEmpty()) {
                        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text(uiState.error)
                        }
                    }

                    uiState.data?.let { data ->
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(data.countries) {
                                ListItem(it)

                            }
                        }


                    }


                }
            }
        }
    }

    @Composable
    private fun ListItem(it: GetCountriesQuery.Country) {
        Column(
        ) {
            Column(modifier=Modifier.padding(horizontal = 16.dp)) {
                Spacer(Modifier.height(8.dp))
                Text(
                    it.name.plus(" ").plus(it.emoji),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 24.sp
                    )
                )
                Spacer(Modifier.height(8.dp))

                Text(
                    it.languages.joinToString { it.name },
                    style = MaterialTheme.typography.bodySmall.copy(
                        fontSize = 16.sp
                    )
                )

                Spacer(Modifier.height(8.dp))
            }

            HorizontalDivider()
        }
    }
}
