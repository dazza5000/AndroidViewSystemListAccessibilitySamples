package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun selectAddress(id: String) {
        val updatedList = _addresses.value!!.map { it.copy(selected = (it.id == id)) }
        _addresses.postValue(updatedList)
    }

    private val testList = listOf(
        Fruit("1", "apple", true),
        Fruit("2", "orange", false),
        Fruit("3", "banana", false),
        Fruit("4", "grape", false),
    )

    private val _addresses: MutableLiveData<List<Fruit>> = MutableLiveData<List<Fruit>>(testList)
    val addresses:LiveData<List<Fruit>>
    get() = _addresses

}