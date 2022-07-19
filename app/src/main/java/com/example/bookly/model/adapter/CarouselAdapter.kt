package com.example.bookly.model.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookly.R
import com.example.bookly.databinding.AdapterCarouselBinding
import com.example.bookly.model.data.CarouselItem
import com.squareup.picasso.Picasso

class CarouselAdapter(
    private val mList: List<CarouselItem>?,
): RecyclerView.Adapter<CarouselAdapter.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter_carousel, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Picasso.get().load( mList?.get(position)?.image)
            .into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return mList!!.size
    }

    inner class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imPhotoCarousel)
        val binding = AdapterCarouselBinding.bind(itemView)


    }
}