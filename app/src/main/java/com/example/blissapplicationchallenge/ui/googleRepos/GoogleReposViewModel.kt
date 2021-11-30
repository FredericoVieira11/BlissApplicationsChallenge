package com.example.blissapplicationchallenge.ui.googleRepos

import androidx.lifecycle.*
import com.example.blissapplicationchallenge.network.repository.googleReposRepository.GoogleReposRepository
import com.example.blissapplicationchallenge.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

const val SIZE = 20
const val PAGE = 1

@HiltViewModel
class GoogleReposViewModel @Inject constructor(
    private val repository: GoogleReposRepository
): ViewModel() {

    fun getGoogleRepos() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getGoogleReposList("google", PAGE, SIZE)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

}