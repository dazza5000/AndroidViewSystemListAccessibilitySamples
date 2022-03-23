package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.listoptions

import android.os.Bundle
import android.text.SpannableString
import android.text.style.BulletSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.SpannableFragmentBinding
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.AddressViewHolderListener
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.model.Fruit
import com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.MainViewModel


class SpannableFragment : Fragment() {

    companion object {
        fun newInstance() = SpannableFragment()
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

    private lateinit var binding: SpannableFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = SpannableFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.addresses.observe(viewLifecycleOwner) {
            setText(it)
        }
    }

    private fun setText(fruit: List<Fruit>) {
        binding.spannableTextView.text = fruit.map { it.name }.toBulletedList()
    }

    fun List<String>.toBulletedList(): CharSequence {
        return SpannableString(this.joinToString("\n")).apply {
            this@toBulletedList.foldIndexed(0) { index, string, span ->
                val end = string + span.length + if (index != this@toBulletedList.size - 1) 1 else 0
                this.setSpan(BulletSpan(16), string, end, 0)
                end
            }
        }
    }

}