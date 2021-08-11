package uz.revolution.viewpagertask2

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.tab_item.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import uz.revolution.viewpagertask2.adapters.CategoryAdapter
import uz.revolution.viewpagertask2.models.Category
import uz.revolution.viewpagertask2.models.Photo
import uz.revolution.viewpagertask2.retrofit.Common
import uz.revolution.viewpagertask2.retrofit.RetrofitService

class MainActivity : AppCompatActivity() {

    lateinit var categoryAdapter: CategoryAdapter
    lateinit var retrofitService: RetrofitService

    lateinit var data: ArrayList<Category>
    lateinit var allList: ArrayList<Photo>
    lateinit var newList: ArrayList<Photo>
    lateinit var animalList: ArrayList<Photo>
    lateinit var technologyList: ArrayList<Photo>
    lateinit var natureList: ArrayList<Photo>
    lateinit var sportList: ArrayList<Photo>

    private val client_id = "iwZJGTKMkxVTNkg4dAMwQpsCFI2QUFIk5ooyGCxhvfY"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        retrofitService = Common.retrofitService
        data = ArrayList()
        allList = ArrayList()
        newList = ArrayList()
        animalList = ArrayList()
        technologyList = ArrayList()
        natureList = ArrayList()
        sportList = ArrayList()
        categoryAdapter = CategoryAdapter(supportFragmentManager, data)
        loadAllData()
        loadNewData()
        loadAnimalData()
        loadTechnologyData()
        loadNatureData()
        loadSportData()
        loadAdapter()
    }

    private fun loadAllData() {
        retrofitService
            .getPhotos(client_id, 120, "")
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        allList = response.body() as ArrayList
//                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadNewData() {
        retrofitService
            .getNewPhotos(client_id, 120, "latest")
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        newList = response.body() as ArrayList
//                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadAnimalData() {
        retrofitService
            .getQueryPhotos("random", client_id, "animal", 120)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        animalList = response.body() as ArrayList
//                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadTechnologyData() {
        retrofitService
            .getQueryPhotos("random", client_id, "technology", 120)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        technologyList = response.body() as ArrayList
//                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadNatureData() {
        retrofitService
            .getQueryPhotos("random", client_id, "nature", 120)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        natureList = response.body() as ArrayList
//                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadSportData() {
        retrofitService
            .getQueryPhotos("random", client_id, "sport", 120)
            .enqueue(object : Callback<List<Photo>> {
                override fun onResponse(call: Call<List<Photo>>, response: Response<List<Photo>>) {
                    if (response.isSuccessful && response.body() != null) {
                        sportList = response.body() as ArrayList
                        loadData()
                        categoryAdapter.notifyDataSetChanged()
                        setTabs()
                    }
                }

                override fun onFailure(call: Call<List<Photo>>, t: Throwable) {
                    Log.d("AAAA", "onError: ${t.message}")
                }

            })
    }

    private fun loadAdapter() {
        view_pager.adapter = categoryAdapter
        tab_layout.setupWithViewPager(view_pager)
        setTabs()
    }

    private fun loadData() {
        data.add(Category("All", allList))
        data.add(Category("New", newList))
        data.add(Category("Animals", animalList))
        data.add(Category("Technology", technologyList))
        data.add(Category("Nature", natureList))
        data.add(Category("Sport", sportList))
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

        tab_layout.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
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
}