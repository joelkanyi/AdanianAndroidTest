package com.kanyideveloper.adanianandroidtest.presentation.images

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kanyideveloper.adanianandroidtest.domain.model.Image
import com.kanyideveloper.adanianandroidtest.domain.use_case.GetImages
import com.kanyideveloper.adanianandroidtest.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ImagesViewModel @Inject constructor(
    private val getImages: GetImages
) : ViewModel() {

    private val _searchQuery = MutableLiveData("dog")
    val searchQuery: LiveData<String?> = _searchQuery

    fun onSearch(query: String): Flow<Resource<List<Image>>> {
        _searchQuery.value = query
        return getImages(query)
    }
}