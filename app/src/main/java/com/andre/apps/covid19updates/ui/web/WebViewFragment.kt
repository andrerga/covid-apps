package com.andre.apps.covid19updates.ui.web

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andre.apps.covid19updates.databinding.WebViewFragmentBinding

class WebViewFragment : Fragment() {

    private var _binding: WebViewFragmentBinding? = null
    private val binding get() = _binding!!

    private val args: WebViewFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = WebViewFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val transitionName = args.transitionName
            transitionName?.run {
                binding.parent.transitionName = this
            }
        }

        binding.mainWeb.run {
            settings.javaScriptEnabled = true
            settings.domStorageEnabled = true
            webViewClient = WebClient()

            resumeTimers()
            loadUrl(args.url)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        binding.mainWeb.run {
            clearHistory()
            clearCache(true)
            onPause()
            removeAllViews()

            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P)
                destroyDrawingCache()

            pauseTimers()
            destroy()
        }
    }

    private inner class WebClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }

        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            view.loadUrl(request.url.toString())
            return true
        }
    }
}