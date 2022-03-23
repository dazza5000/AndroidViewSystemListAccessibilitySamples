package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_FOCUSED
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.ListItemAddressBinding


class FruitAdapter(private val listener: AddressViewHolderListener) :
    ListAdapter<Fruit, RecyclerView.ViewHolder>(AddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddressViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as AddressViewHolder).bind(plant)
    }

    class AddressViewHolder(
        private val binding: ListItemAddressBinding,
        private val listener: AddressViewHolderListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(fruit: Fruit) {
            Log.d("darran", "Binding address: $fruit")

            binding.addressCheckBox.isChecked = fruit.selected
            binding.addressCheckBox.tag = fruit.id
            binding.addressCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked) {
                    listener.onAddressChecked(buttonView.tag as String)
                }
            }

            binding.addressId.text = fruit.id
            binding.addressName.text = fruit.name

            binding.root.setOnClickListener {
                binding.addressCheckBox.performClick()
            }

            if (fruit.selected && listener.shouldAddressRequestFocus(fruit.id)) {
                binding.root.run {


                        Log.d("darran", "requesting focus for address: $fruit")

                        requestFocus()
                        sendAccessibilityEvent(TYPE_VIEW_FOCUSED)

                }
            }
        }

        companion object {
            fun from(
                parent: ViewGroup,
                listener: AddressViewHolderListener
            ): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAddressBinding.inflate(layoutInflater, parent, false)

                return AddressViewHolder(binding, listener)
            }
        }
    }
}

private class AddressDiffCallback : DiffUtil.ItemCallback<Fruit>() {

    override fun areItemsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Fruit, newItem: Fruit): Boolean {
        return oldItem == newItem
    }
}

interface AddressViewHolderListener {
    fun onAddressChecked(id: String)
    fun shouldAddressRequestFocus(id: String): Boolean
}
