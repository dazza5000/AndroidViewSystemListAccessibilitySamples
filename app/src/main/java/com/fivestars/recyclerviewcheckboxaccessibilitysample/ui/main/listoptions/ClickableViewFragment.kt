package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.listoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.ClickableFragmentBinding
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.StringFragmentBinding
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.WebviewFragmentBinding


class ClickableViewFragment : Fragment() {

    companion object {
        fun newInstance() = ClickableViewFragment()
    }

    private lateinit var binding: ClickableFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ClickableFragmentBinding.inflate(inflater, container, false)
        binding.clickableTextView.setOnClickListener {

        }
        return binding.root
    }


}