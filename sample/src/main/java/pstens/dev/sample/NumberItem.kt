package pstens.dev.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pstens.dev.vpager.VPagerItem

class NumberItem(items: List<Int>) : VPagerItem<Int>(items) {

    override fun filterBySearchQuery(query: String) = items.filter {
        "$it".contains(query, ignoreCase = true)
    }

    override fun getPageTitle(): String = "NumberItem (${items.size})"

    override fun shouldShow(): Boolean = true

    override fun createViewHolder(
        parent: ViewGroup,
        inflater: LayoutInflater
    ): RecyclerView.ViewHolder {
        val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
        return object : RecyclerView.ViewHolder(view) {}
    }

    override fun bindToViewHolder(item: Int, holder: RecyclerView.ViewHolder) {
        holder.itemView.findViewById<TextView>(android.R.id.text1).text = "$item"
    }
}