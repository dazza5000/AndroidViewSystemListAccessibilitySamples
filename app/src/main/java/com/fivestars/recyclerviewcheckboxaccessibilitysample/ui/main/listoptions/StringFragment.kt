package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.listoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.StringFragmentBinding


class StringFragment : Fragment() {

    companion object {
        fun newInstance() = StringFragment()
    }

    private lateinit var binding: StringFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = StringFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}