package com.andre.apps.covid19updates.ui.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.recyclerview.widget.DiffUtil
import com.andre.apps.covid19updates.common.BoundPagedListAdapter
import com.andre.apps.covid19updates.core.feature.news.model.NewsItem
import com.andre.apps.covid19updates.core.util.parseToString
import com.andre.apps.covid19updates.databinding.NewsListItemBinding

class NewsAdapter(
    private val viewModel: NewsViewModel
) : BoundPagedListAdapter<NewsItem, NewsListItemBinding>(
    diffCallback = object : DiffUtil.ItemCallback<NewsItem>() {
        override fun areItemsTheSame(
            oldItem: NewsItem,
            newItem: NewsItem
        ): Boolean = false

        override fun areContentsTheSame(
            oldItem: NewsItem,
            newItem: NewsItem
        ): Boolean = oldItem.headline == newItem.headline
    }
) {

    override fun createBinding(parent: ViewGroup, viewType: Int): NewsListItemBinding =
        NewsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

    override fun bind(binding: NewsListItemBinding, item: NewsItem) {
        binding.headlineText.text = item.headline
        binding.dateText.text = item.date.parseToString("dd MMM yyyy HH:mm")
        binding.sourceText.text = item.source.toString()

        // This is required to add transition name
        ViewCompat.setTransitionName(binding.parent, item.headline)

        val extras = FragmentNavigatorExtras(
            binding.parent to item.headline
        )

        if (!binding.parent.hasOnClickListeners())
            binding.parent.setOnClickListener {
                viewModel.openWeb(item.contentUrl, item.headline, extras)
            }
    }
}