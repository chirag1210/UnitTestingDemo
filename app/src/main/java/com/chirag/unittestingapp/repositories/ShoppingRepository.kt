package com.chirag.unittestingapp.repositories

import com.chirag.unittestingapp.data.remote.responses.ImageResponse
import com.chirag.unittestingapp.util.Resource

interface ShoppingRepository {
    suspend fun getUsers(imageQuery: String): Resource<ImageResponse>
}