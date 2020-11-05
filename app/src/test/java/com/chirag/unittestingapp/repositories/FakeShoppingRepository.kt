package com.chirag.unittestingapp.repositories

import com.chirag.unittestingapp.data.remote.responses.ImageResponse
import com.chirag.unittestingapp.util.Resource

class FakeShoppingRepository : ShoppingRepository {

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun getUsers(imageQuery: String): Resource<ImageResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ImageResponse(listOf(), 0, 0))
        }
    }
}