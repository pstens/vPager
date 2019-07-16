package pstens.dev.vpager

import androidx.recyclerview.widget.RecyclerView

abstract class SearchableAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    abstract fun applySearchQuery(query: String)
}

abstract class VPagerItem<T>(val items: List<T>) {
    abstract fun getPageTitle(): String

    abstract fun shouldShow(): Boolean

    abstract fun filterBySearchQuery(query: String): List<T>

    abstract fun getAdapter(): SearchableAdapter
}
