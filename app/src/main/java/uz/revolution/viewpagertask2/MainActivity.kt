package uz.revolution.viewpagertask2

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_item.view.*
import uz.revolution.viewpagertask2.adapters.CategoryAdapter
import uz.revolution.viewpagertask2.models.Category

class MainActivity : AppCompatActivity() {

    lateinit var data: ArrayList<Category>
    lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val allList = ArrayList<String>()
        val newList = ArrayList<String>()
        val animalList = ArrayList<String>()
        val technologyList = ArrayList<String>()
        val natureList = ArrayList<String>()
        val sportList = ArrayList<String>()
        for (i in 300..899) {
                allList.add("https://picsum.photos/id/$i/400/700")
            if (i in 400..499) {
                newList.add("https://picsum.photos/id/$i/400/700")
            }
            if (i in 500..599) {
                animalList.add("https://picsum.photos/id/$i/400/700")
            }
            if (i in 600..699) {
                technologyList.add("https://picsum.photos/id/$i/400/700")
            }
            if (i in 700..799) {
                natureList.add("https://picsum.photos/id/$i/400/700")
            }
            if (i in 800..899) {
                sportList.add("https://picsum.photos/id/$i/400/700")
            }
        }

        
        data = ArrayList()

        data.add(Category("All", allList))
        data.add(Category("New", newList))
        data.add(Category("Animals", animalList))
        data.add(Category("Technology", technologyList))
        data.add(Category("Nature", natureList))
        data.add(Category("Sport", sportList))

        categoryAdapter = CategoryAdapter(supportFragmentManager, data)
        view_pager.adapter = categoryAdapter
        tab_layout.setupWithViewPager(view_pager)

        setTabs()

        tab_layout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            @SuppressLint("ResourceAsColor")
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.circle_layout?.visibility = View.VISIBLE
                customView?.title_tv?.setTextColor(resources.getColor(R.color.white))
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val customView = tab?.customView
                customView?.circle_layout?.visibility = View.INVISIBLE
                customView?.title_tv?.setTextColor(resources.getColor(R.color.white50))
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })

    }

    private fun setTabs() {
        val tabCount = tab_layout.tabCount

        for (i in 0 until tabCount) {
            val tabView = LayoutInflater.from(this).inflate(R.layout.tab_item, null, false)
            val tab = tab_layout.getTabAt(i)
            tab?.customView = tabView
            tabView.title_tv.text = data[i].title

            if (i == 0) {
                tabView.circle_layout.visibility = View.VISIBLE
                tabView?.title_tv?.setTextColor(resources.getColor(R.color.white))
            } else {
                tabView.circle_layout.visibility = View.INVISIBLE
                tabView?.title_tv?.setTextColor(resources.getColor(R.color.white50))
            }
        }
    }
}