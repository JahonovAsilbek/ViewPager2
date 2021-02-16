package uz.revolution.viewpagertask2.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import uz.revolution.viewpagertask2.fragments.CategoryFragment
import uz.revolution.viewpagertask2.models.Category

class CategoryAdapter(fragmentManager: FragmentManager,var categoryList:ArrayList<Category>) : FragmentStatePagerAdapter(
    fragmentManager,
    BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT

) {
    override fun getCount(): Int = categoryList.size


    override fun getItem(position: Int): Fragment {
        return CategoryFragment.newInstance(categoryList[position].images)
    }
}