package com.example.softexperttask.home.pagination

import android.annotation.SuppressLint
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.softexperttask.R
import com.example.softexperttask.common.model.Data
import com.squareup.picasso.Picasso


/**
 * Created by ASlm on 1/1/2020
 */
class OrderAdapter() : PagedListAdapter<Data, OrderAdapter.UserViewHolder>(USER_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.cars_item, parent, false)
        return UserViewHolder(view)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val data = getItem(position)
        data?.let { holder.bind(it) }
    }

    inner class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val brandName = view.findViewById<TextView>(R.id.tv_brand_name)
        private val isUsed = view.findViewById<TextView>(R.id.tv_is_used)
        private val construction = view.findViewById<TextView>(R.id.tv_construction)
        private val service_img = view.findViewById<ImageView>(R.id.car_image)

        @SuppressLint("SetTextI18n")
        fun bind(data: Data) {

            brandName.text = data.brand.toString()
            isUsed.text = data.isUsed.toString()
            construction.text = data.constructionYear
            Picasso.get().load(data.imageUrl).placeholder(R.drawable.fb_button_background_selector).into(service_img)


        }
    }

    companion object {
        private val USER_COMPARATOR = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean = oldItem.id == newItem.id
            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean = newItem == oldItem
        }
    }
}