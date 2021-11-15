package com.jan.rappimovies.baseui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

/**
 * Base fragment class
 * @author Jaime Trujillo
 */

abstract class BaseFragment<Binding : ViewBinding>(private val inflate: Inflate<Binding>) :
    Fragment() {

    private lateinit var _binding: Binding
    protected val binding get() = _binding
    private var baseActivity: AppCompatActivity? = null

    override fun onCreateView(
        @NonNull inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = inflate(inflater, container, false)
        return _binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        baseActivity = context as AppCompatActivity?
    }
}