package pstens.dev.vpager

fun vPagerAdapter(block: VPagerAdapter.() -> Unit): VPagerAdapter = VPagerAdapter().apply(block)

@Suppress("UNCHECKED_CAST")
fun <T> VPagerAdapter.addItem(item: VPagerItem<T>) {
    addItem(item as VPagerItem<Any>)
}
