package pstens.dev.vpager

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter

class VPagerAdapter : PagerAdapter() {

    private var inflater: LayoutInflater? = null

    private val items: MutableList<VPagerItem<Any>> = mutableListOf()
    private var shownItems: List<VPagerItem<Any>> = items.toList()
    private var itemToAdapter: MutableMap<VPagerItem<Any>, Adapter> = mutableMapOf()

    internal fun addItem(pagerItem: VPagerItem<Any>) {
        items.add(pagerItem)
        shownItems = items.filter { it.shouldShow() }
    }

    fun applySearchQuery(query: String) {
        itemToAdapter.values.forEach { adapter ->
            adapter.applySearchQuery(query)
        }
    }

    override fun isViewFromObject(view: View, o: Any): Boolean = (view == o)

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): String = items[position].getPageTitle()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        inflater = inflater ?: LayoutInflater.from(container.context)
        return (inflater!!.inflate(R.layout.list, container, false) as RecyclerView).also { rv ->
            val adapter = Adapter(items[position])
            itemToAdapter[items[position]] = adapter
            rv.adapter = adapter
            container.addView(rv)
        }
    }

    override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
        container.removeView(o as View)
    }
}

private class Adapter(val pagerItem: VPagerItem<Any>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var displayedItems: List<Any> = pagerItem.items

    private var inflater: LayoutInflater? = null

    fun applySearchQuery(query: String) {
        displayedItems = pagerItem.filterBySearchQuery(query)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        inflater = inflater ?: LayoutInflater.from(parent.context)
        return pagerItem.createViewHolder(parent, inflater!!)
    }

    override fun getItemCount(): Int = displayedItems.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        pagerItem.bindToViewHolder(displayedItems[position], holder)
    }
}
