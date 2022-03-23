package com.fivestars.recyclerviewcheckboxaccessibilitysample.ui.main.listoptions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.StringFragmentBinding
import com.fivestars.recyclerviewcheckboxaccessibilitysample.databinding.WebviewFragmentBinding


class WebViewFragment : Fragment() {

    companion object {
        fun newInstance() = WebViewFragment()
    }

    private lateinit var binding: WebviewFragmentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = WebviewFragmentBinding.inflate(inflater, container, false)
        binding.webview.loadData("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<h2>Unordered List with Disc Bullets</h2>\n" +
                "\n" +
                "<ul>\n" +
                "  <li>Coffee</li>\n" +
                "  <li>Tea</li>\n" +
                "  <li>Milk</li>\n" +
                "</ul>  \n" +
                "\n" +
                "</body>\n" +
                "</html>\n",
            "text/html",
            "UTF-8"
        )
        return binding.root
    }


}