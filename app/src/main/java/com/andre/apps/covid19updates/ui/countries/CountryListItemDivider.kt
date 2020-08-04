package com.andre.apps.covid19updates.ui.countries

import android.content.Context
import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

class CountryListItemDivider(
    context: Context?
) : RecyclerView.ItemDecoration() {

    private var space = when (context) {
        null -> 10
        else -> (10 * context.resources.displayMetrics.density)
            .roundToInt()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        outRect.top = space

        if (parent.getChildAdapterPosition(view) == state.itemCount - 1)
            outRect.bottom = space
    }
}