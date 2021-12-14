package com.kanyideveloper.adanianandroidtest.presentation.images

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kanyideveloper.adanianandroidtest.R
import com.kanyideveloper.adanianandroidtest.databinding.ImageRowBinding
import com.kanyideveloper.adanianandroidtest.domain.model.Image

class ImagesAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<Image, ImagesAdapter.MyViewModel>(IMAGES_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewModel {
        return MyViewModel(
            ImageRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewModel, position: Int) {
        val image = getItem(position)
        holder.bind(image)

        holder.itemView.setOnClickListener {
            onClickListener.onClick(image)
        }
    }


    inner class MyViewModel(private val binding: ImageRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: Image?) {
            Glide.with(binding.image)
                .load(image?.webformatURL)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.image)
            binding.ownerName.text = image?.user
        }
    }

    companion object {
        private val IMAGES_COMPARATOR = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.previewURL == newItem.previewURL
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }
        }
    }

    class OnClickListener(val clickListener: (image: Image) -> Unit) {
        fun onClick(image: Image) = clickListener(image)
    }
}