package com.example.blissapplicationchallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.blissapplicationchallenge.network.repository.EmojisRepository
import com.example.blissapplicationchallenge.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: EmojisRepository
): ViewModel() {

    fun getEmojis() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getEmojis()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }


}