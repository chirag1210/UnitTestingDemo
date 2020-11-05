package com.chirag.unittestingapp.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.chirag.unittestingapp.data.remote.responses.ImageResponse
import com.chirag.unittestingapp.repositories.ShoppingRepository
import com.chirag.unittestingapp.util.Resource
import com.chirag.unittestingapp.utils.TestCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class ShoppingViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<ImageResponse>>

    @Mock
    private lateinit var shoppingRepository: ShoppingRepository

    @Before
    fun setUp() {
        // do something if required
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val imageResponse = mock(ImageResponse::class.java)
            doReturn(imageResponse)
                .`when`(shoppingRepository)
                .getUsers("corona")
            val viewModel = ShoppingViewModel(shoppingRepository)
            viewModel.getUsers("corona").observeForever(apiUsersObserver)
            verify(shoppingRepository).getUsers("corona")
            verify(apiUsersObserver).onChanged(Resource.success(imageResponse))
            viewModel.getUsers("corona").removeObserver(apiUsersObserver)
        }
    }

}