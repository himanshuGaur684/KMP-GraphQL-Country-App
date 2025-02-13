package dev.himanshu.graphqlcountryapp.viewModel

import com.rickclephas.kmp.nativecoroutines.NativeCoroutinesState
import com.rickclephas.kmp.observableviewmodel.MutableStateFlow
import com.rickclephas.kmp.observableviewmodel.ViewModel
import com.rickclephas.kmp.observableviewmodel.launch
import dev.himanshu.graphqlcountryapp.GetCountriesQuery
import dev.himanshu.graphqlcountryapp.repository.CountriesRepository
import kotlinx.coroutines.flow.asStateFlow
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class CountriesViewModel : ViewModel(), KoinComponent {

    private val repository: CountriesRepository by inject()

    private val _uiState = MutableStateFlow(viewModelScope, UiState())

    @NativeCoroutinesState
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            getCountries()
        }
    }

    suspend fun getCountries() {
        _uiState.value = UiState(isLoading = true)
        val result = repository.getCountries()
        if (result.isSuccess) {
            _uiState.value = UiState(data = result.getOrNull())
        } else {
            _uiState.value = UiState(error = result.exceptionOrNull()?.message.toString())
        }
    }

}


data class UiState(
    val isLoading: Boolean = false,
    val error: String = "",
    val data: GetCountriesQuery.Data? = null
)