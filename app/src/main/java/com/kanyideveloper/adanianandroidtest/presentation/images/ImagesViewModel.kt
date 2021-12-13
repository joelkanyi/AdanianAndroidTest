package com.kanyideveloper.adanianandroidtest.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.adanianandroidtest.domain.use_case.GetImages
import com.kanyideveloper.adanianandroidtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImages: GetImages
) : ViewModel() {

    private val _searchQuery = MutableLiveData("")
    val searchQuery: LiveData<String> = _searchQuery

    private val _state = MutableLiveData(ImagesState())
    val state: LiveData<ImagesState> = _state

    private val _eventFlow = MutableSharedFlow<UIEvent>()
    val eventFlow = _eventFlow

    private var searchJob: Job? = null

    fun onSearch(query: String) {
        _searchQuery.value = query
        viewModelScope.launch {
            getImages(query).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        _state.value = state.value?.copy(
                            images = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                    is Resource.Error -> {
                        _state.value = state.value?.copy(
                            images = result.data ?: emptyList(),
                            isLoading = false
                        )

                        _eventFlow.emit(
                            UIEvent.ShowSnackbar(
                                result.message ?: "Unknown Error"
                            )
                        )
                    }
                    is Resource.Loading -> {
                        _state.value = state.value?.copy(
                            images = result.data ?: emptyList(),
                            isLoading = true
                        )
                    }
                }
            }
        }
    }

    sealed class UIEvent {
        data class ShowSnackbar(val message: String) : UIEvent()
    }
}