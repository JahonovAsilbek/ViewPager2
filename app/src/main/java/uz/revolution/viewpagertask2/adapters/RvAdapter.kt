package uz.revolution.viewpagertask2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_image.view.*
import uz.revolution.viewpagertask2.R

class RvAdapter(var imageList:ArrayList<String>) : RecyclerView.Adapter<RvAdapter.VH>() {

    private var onImageClick:OnImageClick?=null

    inner class VH(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun inBind(string: String,position: Int) {
            Picasso.get().load(string).error(R.drawable.aaaaa).into(itemView.image_view)

            itemView.setOnClickListener {
                if (onImageClick!= null)
                    onImageClick!!.onClick(string)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return VH(LayoutInflater.from(parent.context).inflate(R.layout.item_image,parent,false))
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.inBind(imageList[position],position)
    }

    override fun getItemCount(): Int = imageList.size

    interface OnImageClick{
        fun onClick(string: String)
    }

    fun setOnImageClick(onImageClick: OnImageClick) {
        this.onImageClick=onImageClick
    }

}