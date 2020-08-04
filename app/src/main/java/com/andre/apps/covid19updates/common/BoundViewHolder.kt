package com.andre.apps.covid19updates.common

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

open class BoundViewHolder<out T : ViewBinding>(
    val binding: T
) : RecyclerView.ViewHolder(binding.root), LifecycleOwner {

    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }

    init {
        lifecycleRegistry.currentState = Lifecycle.State.INITIALIZED
    }

    fun markAttach() {
        lifecycleRegistry.currentState = Lifecycle.State.STARTED
    }

    fun markDetach() {
        lifecycleRegistry.currentState = Lifecycle.State.DESTROYED
    }

    override fun getLifecycle(): Lifecycle {
        return lifecycleRegistry
    }
}