package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.MainFragmentBinding

class RowFragment : Fragment() {

    companion object {
        fun newInstance() = RowFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val listener: AddressViewHolderListener = object : AddressViewHolderListener {
        var lastSelectedAddress: String? = null
        override fun onAddressChecked(id: String) {
            lastSelectedAddress = id
            viewModel.selectAddress(id)
        }

        override fun shouldAddressRequestFocus(id: String): Boolean {
            if (lastSelectedAddress == id) {
                lastSelectedAddress = null
                return true
            }
            return false
        }
    }

    private val fruitAdapter: FruitAdapter = FruitAdapter(listener)

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.addressRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.addressRecyclerView.adapter = fruitAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.addresses.observe(viewLifecycleOwner) {
            fruitAdapter.submitList(it)
        }
    }

}