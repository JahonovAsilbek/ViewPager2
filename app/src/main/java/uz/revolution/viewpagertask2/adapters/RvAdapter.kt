package uz.revolution.viewpagertask2.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_image.view.*
import uz.revolution.viewpagertask2.R
import uz.revolution.viewpagertask2.models.Photo

class RvAdapter(var imageList: ArrayList<Photo>, var fragment: Fragment) :
    RecyclerView.Adapter<RvAdapter.VH>() {

    private var onImageClick: OnImageClick? = null

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun onBind(photo: Photo, position: Int) {
//            Picasso.get().load(photo.urls.full).error(R.drawable.aaaaa).into(itemView.image_view)

            Glide.with(fragment).load(photo.urls.small).into(itemView.image_view)


            itemView.setOnClickListener {
                if (onImageClick != null)
                    onImageClick!!.onClick(photo.urls.regular)
                Log.d("AAAA", "onBind: ${photo.urls.regular}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_image, parent, false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(imageList[position], position)
    }

    override fun getItemCount(): Int = imageList.size

    interface OnImageClick {
        fun onClick(string: String)
    }

    fun setOnImageClick(onImageClick: OnImageClick) {
        this.onImageClick = onImageClick
    }

}