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
    private var itemToAdapter: MutableMap<VPagerItem<Any>, SearchableAdapter> = mutableMapOf()

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

    override fun getCount(): Int = shownItems.size

    override fun getPageTitle(position: Int): String = shownItems[position].getPageTitle()

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val item = shownItems[position]
        inflater = inflater ?: LayoutInflater.from(container.context)
        val rv = inflater!!.inflate(R.layout.list, container, false) as RecyclerView
        rv.adapter = item.getAdapter().also { itemToAdapter[item] = it }
        container.addView(rv)
        return rv
    }

    override fun destroyItem(container: ViewGroup, position: Int, o: Any) {
        container.removeView(o as View)
    }
}
