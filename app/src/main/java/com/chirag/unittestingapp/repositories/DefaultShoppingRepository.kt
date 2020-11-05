package com.chirag.unittestingapp.repositories

import com.chirag.unittestingapp.data.remote.PixabayAPI
import com.chirag.unittestingapp.data.remote.responses.ImageResponse
import com.chirag.unittestingapp.util.Resource
import javax.inject.Inject

class DefaultShoppingRepository @Inject constructor(
    private val pixabayAPI: PixabayAPI
) : ShoppingRepository {

    override suspend fun getUsers(imageQuery: String): Resource<ImageResponse> {
        return try {
            val response = pixabayAPI.searchForImage(imageQuery)
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error("An unknown error occured", null)
            } else {
                Resource.error("An unknown error occured", null)
            }
        } catch (e: Exception) {
            Resource.error("Couldn't reach the server. Check your internet connection", null)
        }
    }
}