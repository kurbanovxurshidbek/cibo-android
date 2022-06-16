package com.cibo.cibo.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cibo.cibo.model.CategoryList
import com.cibo.cibo.repository.CategoryRepository
import com.cibo.cibo.utils.UiStateObject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val repository: CategoryRepository
) : ViewModel() {

    private val _newsState = MutableStateFlow<UiStateObject<CategoryList>>(UiStateObject.EMPTY)
    val newsState = _newsState

    fun getCategories(filialId: String, language: String) = viewModelScope.launch {
        _newsState.value = UiStateObject.LOADING

        try {
            val response = repository.getCategories(filialId, language)
            _newsState.value = UiStateObject.SUCCESS(response)
        } catch (e: Exception) {
            _newsState.value = UiStateObject.ERROR(e.localizedMessage ?: "No Connection")
        }

    }

}