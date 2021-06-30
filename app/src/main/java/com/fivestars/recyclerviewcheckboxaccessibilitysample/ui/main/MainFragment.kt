package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_FOCUSED
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.MainFragmentBinding
import android.view.ViewTreeObserver.OnGlobalLayoutListener as OnGlobalLayoutListener1


class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var recyclerViewReadyCallback: RecyclerViewReadyCallback? = null

    private lateinit var viewModel: MainViewModel
    private val listener: AddressViewHolderListener = object : AddressViewHolderListener {
        var lastSelectedAddress: String? = null
        override fun onAddressChecked(id: String, position: Int) {
            lastSelectedAddress = id

            Log.d("darran", "address checked position is: $position")


            recyclerViewReadyCallback = object : RecyclerViewReadyCallback {
                override fun onLayoutReady() {
                    val viewHolder = binding.addressRecyclerView.findViewHolderForLayoutPosition(position)
                    Log.d("darran", "adapter position is: $viewHolder")
                    viewHolder?.itemView?.sendAccessibilityEvent(TYPE_VIEW_FOCUSED)
                    viewHolder?.itemView?.requestFocus()
                }
            }

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

    private val addressAdapter: AddressAdapter = AddressAdapter(listener)

    private lateinit var binding: MainFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(inflater, container, false)
        binding.addressRecyclerView.adapter = addressAdapter
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.addresses.observe(viewLifecycleOwner) {
            addressAdapter.submitList(it)
            binding.addressRecyclerView.viewTreeObserver
                .addOnGlobalLayoutListener(object : OnGlobalLayoutListener1 {
                    override fun onGlobalLayout() {
                        recyclerViewReadyCallback?.onLayoutReady()
                        binding.addressRecyclerView.viewTreeObserver.removeOnGlobalLayoutListener(
                            this
                        )
                    }
                })
        }
    }

    interface RecyclerViewReadyCallback {
        fun onLayoutReady()
    }

}