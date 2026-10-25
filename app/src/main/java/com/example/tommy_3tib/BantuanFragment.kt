package com.example.tommy_3tib

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.tommy_3tib.databinding.FragmentBantuanfragmentBinding

class BantuanFragment : Fragment() {
    private lateinit var binding: FragmentBantuanfragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBantuanfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.webView.webViewClient = WebViewClient()
        binding.webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView, newProgress: Int) {
                binding.progressBar.progress = newProgress
                binding.progressBar.visibility =
                    if (newProgress < 100) View.VISIBLE else View.GONE
            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://www.halodoc.com/kesehatan-mental")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.webView.destroy()
    }
}