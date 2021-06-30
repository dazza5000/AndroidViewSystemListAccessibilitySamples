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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.ListItemAddressBinding


class AddressAdapter : ListAdapter<Address, RecyclerView.ViewHolder>(AddressDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AddressViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as AddressViewHolder).bind(plant)
    }

    class AddressViewHolder(
        private val binding: ListItemAddressBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Address) {
            binding.addressCheckBox.isChecked = item.selected
            binding.addressId.text = item.id
            binding.addressName.text = item.name
        }

        companion object {
            fun from(parent: ViewGroup): RecyclerView.ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListItemAddressBinding.inflate(layoutInflater, parent, false)

                return AddressViewHolder(binding)
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
