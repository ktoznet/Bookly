package com.example.bookly.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R
import com.example.bookly.databinding.AdapterRcViewMainBinding
import com.example.bookly.model.data.BestSellersItem
import com.squareup.picasso.Picasso

class MainAdapter(
    private val mList: List<BestSellersItem>?,
    val mItemClickListener: ItemClicklistener
): RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    interface ItemClicklistener {
        fun onItemClick(position: Int, books: BestSellersItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_rc_view_main, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load( mList?.get(position)?.image)
            .into(holder.imageView)
        holder.binding.tvName.text = mList?.get(position)?.title
        holder.binding.tvDescription.text = mList?.get(position)?.author
        holder.binding.tvMoney.text = mList?.get(position)?.price.toString()
        holder.binding.tvGrade.text = mList?.get(position)?.rate?.score.toString()
    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imPhoto)
        val binding = AdapterRcViewMainBinding.bind(itemView)

        init {
            ItemView.setOnClickListener {
                mList?.get(position)?.id?.let { it ->
                    mItemClickListener.onItemClick(it,books = mList.get(position))
                }
            }
        }
    }
}