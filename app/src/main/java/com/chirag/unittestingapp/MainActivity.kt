package com.chirag.unittestingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.chirag.unittestingapp.util.Status
import com.chirag.unittestingapp.viewmodel.ShoppingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: ShoppingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.e("--->", "" + mainViewModel.hashCode())
        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        mainViewModel.getUsers("Corona").observe(this, Observer {
            it?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        val urls = result.data?.hits?.map { imageResult -> imageResult.previewURL }
                    }
                    Status.ERROR -> {

                    }
                    Status.LOADING -> {
                    }
                }
            }
        })
    }
}