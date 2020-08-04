package com.andre.apps.covid19updates.common

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.viewbinding.ViewBinding

abstract class BoundPagedListAdapter<T, V : ViewBinding>(
    diffCallback: DiffUtil.ItemCallback<T>
) : PagedListAdapter<T, BoundViewHolder<V>>(
    AsyncDifferConfig.Builder<T>(diffCallback).build()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoundViewHolder<V> {
        val binding = createBinding(parent, viewType)
        return BoundViewHolder(binding)
    }

    protected abstract fun createBinding(parent: ViewGroup, viewType: Int): V

    override fun onBindViewHolder(holder: BoundViewHolder<V>, position: Int) {
        if (position < itemCount) {
            val t = getItem(position) ?: return
            bind(holder.binding, t)
        }
    }

    override fun onViewAttachedToWindow(holder: BoundViewHolder<V>) {
        super.onViewAttachedToWindow(holder)
        holder.markAttach()
    }

    override fun onViewDetachedFromWindow(holder: BoundViewHolder<V>) {
        super.onViewDetachedFromWindow(holder)
        holder.markDetach()
    }

    protected abstract fun bind(binding: V, item: T)
}