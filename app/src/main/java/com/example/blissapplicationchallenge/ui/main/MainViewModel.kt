package com.example.blissapplicationchallenge.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.blissapplicationchallenge.network.repository.emojisRepository.EmojisRepository
import com.example.blissapplicationchallenge.network.repository.avatarRespository.AvatarRepository
import com.example.blissapplicationchallenge.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val emojisRepository: EmojisRepository,
    private val avatarRepository: AvatarRepository
): ViewModel() {

    fun getEmojis() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = emojisRepository.getEmojis()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

    fun getAvatar(avatar: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = avatarRepository.getAvatar(avatar = avatar)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

}