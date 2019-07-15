package pstens.dev.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import pstens.dev.vpager.addItem
import pstens.dev.vpager.vPagerAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = vPagerAdapter {
            addItem(NumberItem(listOf(1, 2, 3)))
            addItem(NumberItem(listOf(4, 5, 6)))
            addItem(TextItem(listOf("Lorem", "Ipsum", "Dolor")))
        }
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        val fab: FloatingActionButton = findViewById(R.id.fab)

        fab.setOnClickListener { view ->
            if (view.tag == 0) {
                sectionsPagerAdapter.applySearchQuery("o")
                view.tag = 1
            } else {
                sectionsPagerAdapter.applySearchQuery("1")
                view.tag = 0
            }
        }
    }
}
