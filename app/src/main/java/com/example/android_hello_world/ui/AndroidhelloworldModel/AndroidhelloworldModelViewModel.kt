package com.example.android_hello_world.ui.androidhelloworldmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import com.example.android_hello_world.data.AndroidhelloworldModelRepository
import com.example.android_hello_world.ui.androidhelloworldmodel.AndroidhelloworldModelUiState.Error
import com.example.android_hello_world.ui.androidhelloworldmodel.AndroidhelloworldModelUiState.Loading
import com.example.android_hello_world.ui.androidhelloworldmodel.AndroidhelloworldModelUiState.Success
import javax.inject.Inject

@HiltViewModel
class AndroidhelloworldModelViewModel @Inject constructor(
    private val androidhelloworldModelRepository: AndroidhelloworldModelRepository
) : ViewModel() {

    val uiState: StateFlow<AndroidhelloworldModelUiState> = androidhelloworldModelRepository
        .myModels.map<List<String>, AndroidhelloworldModelUiState>(::Success)
        .catch { emit(Error(it)) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Loading)

    fun addAndroidhelloworldModel(name: String) {
        viewModelScope.launch {
            androidhelloworldModelRepository.add(name)
        }
    }
}

sealed interface AndroidhelloworldModelUiState {
    object Loading : AndroidhelloworldModelUiState
    data class Error(val throwable: Throwable) : AndroidhelloworldModelUiState
    data class Success(val data: List<String>) : AndroidhelloworldModelUiState
}