package pstens.dev.vpager

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class VPagerItem<T>(val items: List<T>) {
    abstract fun getPageTitle(): String
    abstract fun shouldShow(): Boolean
    abstract fun filterBySearchQuery(query: String): List<T>
    abstract fun createViewHolder(parent: ViewGroup, inflater: LayoutInflater): RecyclerView.ViewHolder
    abstract fun bindToViewHolder(item: T, holder: RecyclerView.ViewHolder)
}
