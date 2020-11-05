package com.chirag.unittestingapp.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag.unittestingapp.data.remote.responses.ImageResponse
import com.chirag.unittestingapp.repositories.ShoppingRepository
import com.chirag.unittestingapp.util.Event
import com.chirag.unittestingapp.util.Resource
import kotlinx.coroutines.launch

class ShoppingViewModel @ViewModelInject constructor(
    private val shoppingRepository: ShoppingRepository
) : ViewModel() {

    private val _images = MutableLiveData<Resource<ImageResponse>>()
    val images: LiveData<Resource<ImageResponse>> = _images

    fun getUsers(imageQuery: String): LiveData<Resource<ImageResponse>> {
        _images.value = Resource.loading(null)
        viewModelScope.launch {
            val response = shoppingRepository.getUsers(imageQuery)
            _images.value = response
        }
        return images
    }

}