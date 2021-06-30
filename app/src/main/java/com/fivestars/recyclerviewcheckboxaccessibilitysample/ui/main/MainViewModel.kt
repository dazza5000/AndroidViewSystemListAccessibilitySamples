package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun selectAddress(id: String) {
        val existingList = addresses.value
        val updatedList = existingList?.map { it.copy(selected = (it.id == id)) }
        addresses.postValue(updatedList)
    }

    private val testList = listOf(
        Address("1", "first", true),
        Address("2", "second", false),
        Address("3", "third", false),
        Address("4", "fourth", false),
    )

    val addresses: MutableLiveData<List<Address>> = MutableLiveData<List<Address>>(testList)
}