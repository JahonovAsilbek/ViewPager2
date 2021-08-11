package uz.revolution.viewpagertask2.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_category.view.*
import uz.revolution.viewpagertask2.R
import uz.revolution.viewpagertask2.adapters.RvAdapter
import uz.revolution.viewpagertask2.models.Photo

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
    private var param1: ArrayList<Photo>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as ArrayList<Photo>
        }
    }

    lateinit var root: View
    lateinit var rvAdapter: RvAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        root = inflater.inflate(R.layout.fragment_category, container, false)

        rvAdapter = RvAdapter(param1!!, this)
        root.rv.adapter = rvAdapter

        rvAdapter.setOnImageClick(object : RvAdapter.OnImageClick {
            override fun onClick(url: String) {
                val fragment = ImageFragment.newInstance(url)

                fragmentManager?.beginTransaction()?.add(R.id.container, fragment)
                    ?.addToBackStack("fragment")?.commit()

            }

        })


        return root

    }

    companion object {

        @JvmStatic
        fun newInstance(param1: ArrayList<Photo>) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}