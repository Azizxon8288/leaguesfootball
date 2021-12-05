package com.example.playfootballmvvm.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.playfootballmvvm.repository.SecondRepository
import com.example.playfootballmvvm.repository.SortRepository
import com.example.playfootballmvvm.utils.NetworkHelper

class ViewModelFactory(private val sortRepository: SortRepository,private val networkHelper: NetworkHelper,private val secondRepository: SecondRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModelFirst::class.java)) {
            return ViewModelFirst(sortRepository, networkHelper,secondRepository) as T
        }
        throw  Exception("Error")
    }
}