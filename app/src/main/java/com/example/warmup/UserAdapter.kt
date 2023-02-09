package com.example.warmup

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.warmup.Model.*
import com.google.android.material.imageview.ShapeableImageView

class UserAdapter(val context: Context, val items: ArrayList<UserModelClass>) :
    RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    lateinit var templistener: onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_user_layout, // which will be use in the mainActivity
                parent,
                false
            ),
            templistener
        )
    }

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        templistener = listener
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        /*holder.type.text = item.type
        holder.teaserText.text = item.teaserText
        holder.teaserVideo.text = item.teaserVideo.toString()
        holder.showurl.text = item.showUrl
        holder.id.text = item.id
        holder.description.text = item.description*/
        //holder.teaserImage. = item.teaserImage?.alternativeImageUrl
        val item = items.get(position)
        Glide.with(context).load(item.teaserImage?.alternativeImageUrl).into(holder.teaserImage) // Get the picture
        holder.title.text = item.title
    }

    /**
     * Gets the number of items in the list
     */
    override fun getItemCount(): Int {
        return items.size
    }

    /**
     * A ViewHolder describes an item view and metadata about its place within the RecyclerView.
     */
    inner class ViewHolder(view: View, mylistener: onItemClickListener) : RecyclerView.ViewHolder(view) {

        //Holds the TextView that will add each item to
//        val type = view.findViewById<TextView>(R.id.type)
//        val teaserText = view.findViewById<TextView>(R.id.teaserText)
//        val teaserVideo = view.findViewById<TextView>(R.id.teaserVideo)
//        val showurl = view.findViewById<TextView>(R.id.showUrl)
//        val teaserImage = view.findViewById<TextView>(R.id.teaserImage)
//        val id = view.findViewById<TextView>(R.id.id)
//        val description = view.findViewById<TextView>(R.id.description)
        val teaserImage = view.findViewById<ShapeableImageView>(R.id.image_content)
        val title = view.findViewById<TextView>(R.id.tv_title)

        init {
            view.setOnClickListener {
                mylistener.onItemClick(adapterPosition)
            }
        }
    }
}