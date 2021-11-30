package com.example.blissapplicationchallenge.ui.emojiList

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.blissapplicationchallenge.network.repository.emojisRepository.EmojisRepository
import com.example.blissapplicationchallenge.network.resource.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class EmojiListViewModel @Inject constructor(
    private val repository: EmojisRepository
): ViewModel(){

    private val isToReloadDta = MutableLiveData<Boolean>()

    init {
        this.isToReloadDta.value = false
    }

    fun getEmojis() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.getEmojis()))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred"))
        }
    }

    fun setIsToReloadData(isToReloadData: Boolean) {
        this.isToReloadDta.value = isToReloadData
    }

    fun getIsToReloadData(): Boolean = this.isToReloadDta.value!!

}