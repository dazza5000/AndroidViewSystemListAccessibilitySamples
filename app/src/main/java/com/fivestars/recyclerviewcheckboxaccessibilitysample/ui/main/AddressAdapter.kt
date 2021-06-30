/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.ListItemAddressBinding


class AddressAdapter(private val listener: AddressViewHolderListener) :
    ListAdapter<Address, RecyclerView.ViewHolder>(AddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddressViewHolder.from(parent, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as AddressViewHolder).bind(plant, position)
    }

    class AddressViewHolder(
        private val binding: ListItemAddressBinding,
        private val listener: AddressViewHolderListener
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(address: Address, position: Int) {
            Log.d("darran", "Binding address: $address")

            binding.addressCheckBox.isChecked = address.selected
            binding.addressCheckBox.tag = address.id
            binding.addressCheckBox.setOnCheckedChangeListener { buttonView, isChecked ->
                if (isChecked && !address.selected) {
                    Log.d("darran", "checkbox for address: $address")
                    listener.onAddressChecked(buttonView.tag as String, position)
                }
            }

            binding.addressId.text = address.id
            binding.addressName.text = address.name

            binding.root.setOnClickListener {
                binding.addressCheckBox.performClick()
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

private class AddressDiffCallback : DiffUtil.ItemCallback<Address>() {

    override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem == newItem
    }
}

interface AddressViewHolderListener {
    fun onAddressChecked(id: String, position: Int)
    fun shouldAddressRequestFocus(id: String): Boolean
}
