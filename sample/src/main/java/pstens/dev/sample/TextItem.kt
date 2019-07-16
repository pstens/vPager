package pstens.dev.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import pstens.dev.vpager.SearchableAdapter
import pstens.dev.vpager.VPagerItem

class TextItem(items: List<String>) : VPagerItem<String>(items) {

    override fun getAdapter(): SearchableAdapter = object : SearchableAdapter() {
        override fun applySearchQuery(query: String) {
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val view = inflater.inflate(android.R.layout.simple_list_item_1, parent, false)
            return object : RecyclerView.ViewHolder(view) {}
        }

        override fun getItemCount() = items.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            holder.itemView.findViewById<TextView>(android.R.id.text1).text = items[position]
        }

    }

    override fun getPageTitle(): String = "TextItem (${items.size})"

    override fun shouldShow(): Boolean = true
}