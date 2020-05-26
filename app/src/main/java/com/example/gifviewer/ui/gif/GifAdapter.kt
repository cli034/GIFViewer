package com.example.gifviewer.ui.gif

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gifviewer.R
import com.example.gifviewer.databinding.ListItemGifsBinding
import com.example.gifviewer.domain.model.GifObject

class GifAdapter (
    private val onGifItemClicked: (GifObject) -> Unit
) : ListAdapter<GifObject, GifAdapter.ViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.list_item_gifs,
                parent,
                false
            )
        ).apply {
            binding.root.setOnClickListener {
                onGifItemClicked(getItem(adapterPosition))
            }
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        val binding: ListItemGifsBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(gifObject: GifObject) {
            val tinyGifUrl = gifObject.media.firstOrNull()?.tinygif?.url
            with(binding) {
                Glide.with(itemView.context)
                    .asGif()
                    .load(tinyGifUrl)
                    .into(ivGif)
            }

        }
    }

    object DiffCallback : DiffUtil.ItemCallback<GifObject>() {
        override fun areItemsTheSame(oldItem: GifObject, newItem: GifObject): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GifObject, newItem: GifObject): Boolean {
            return oldItem == newItem
        }

    }
}