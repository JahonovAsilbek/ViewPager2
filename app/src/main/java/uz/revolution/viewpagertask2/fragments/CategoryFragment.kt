package uz.revolution.viewpagertask2.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_category.*
import kotlinx.android.synthetic.main.fragment_category.view.*
import uz.revolution.viewpagertask2.R
import uz.revolution.viewpagertask2.adapters.RvAdapter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: ArrayList<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getStringArrayList(ARG_PARAM1)
        }
    }

    lateinit var root:View
    lateinit var rvAdapter: RvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        root = inflater.inflate(R.layout.fragment_category, container, false)

        rvAdapter = RvAdapter(param1!!)
        root.rv.adapter = rvAdapter

        rvAdapter.setOnImageClick(object : RvAdapter.OnImageClick {
            override fun onClick(string: String) {
                val fragment = ImageFragment.newInstance(string)

                fragmentManager?.beginTransaction()?.add(R.id.container, fragment)?.addToBackStack("fragment")?.commit()

            }

        })


        return root

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: ArrayList<String>) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putStringArrayList(ARG_PARAM1, param1)
                }
            }
    }
}