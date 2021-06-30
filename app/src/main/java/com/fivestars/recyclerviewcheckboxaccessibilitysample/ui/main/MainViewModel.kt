package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    fun selectAddress(id: String) {
        val existingList = _addresses.value
        val updatedList = existingList?.map { it.copy(selected = (it.id == id)) }
        updatedList?.run {
            _addresses.postValue(this)
        }
    }

    private val testList = listOf(
        Address("1", "first", true),
        Address("2", "second", false),
        Address("3", "third", false),
        Address("4", "fourth", false),
    )

    private val _addresses: MutableLiveData<List<Address>> =
        MutableLiveData<List<Address>>(testList)
    val addresses: LiveData<List<Address>>
        get() = _addresses

}