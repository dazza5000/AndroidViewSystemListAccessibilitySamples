package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fivestars.recyclerviewcheckboxaccessibilitysample.R
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private val addressAdapter: AddressAdapter = AddressAdapter()

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.addressRecyclerView.adapter = addressAdapter
        return binding.root    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.addresses.observe(viewLifecycleOwner) {
            addressAdapter.submitList(it)
        }
    }

}