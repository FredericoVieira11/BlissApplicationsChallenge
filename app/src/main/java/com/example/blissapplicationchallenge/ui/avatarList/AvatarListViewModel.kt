package com.example.blissapplicationchallenge.ui.avatarList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.blissapplicationchallenge.network.model.AvatarModel
import com.example.blissapplicationchallenge.network.repository.avatarRespository.AvatarRepository
import com.example.blissapplicationchallenge.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class AvatarListViewModel @Inject constructor(
    private val repository: AvatarRepository
): ViewModel(){

    fun getAvatarList() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getAvatarList()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

    fun deleteAvatar(avatar: AvatarModel) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.deleteAvatar(avatar)))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

}