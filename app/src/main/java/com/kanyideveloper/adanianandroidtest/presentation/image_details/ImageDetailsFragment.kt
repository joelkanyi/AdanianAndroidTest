package com.kanyideveloper.adanianandroidtest.presentation.image_details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.kanyideveloper.adanianandroidtest.R
import com.kanyideveloper.adanianandroidtest.databinding.FragmentImageDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImageDetailsFragment : Fragment() {

    private lateinit var binding: FragmentImageDetailsBinding
    private val viewModel: ImageDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.imageDetails.observe(viewLifecycleOwner, { image ->
            Glide.with(binding.imageView)
                .load(image?.webformatURL)
                .placeholder(R.drawable.image_placeholder)
                .into(binding.imageView)

            binding.textViewLikes.text = image?.likes.toString()
            binding.textViewViews.text = image?.views.toString()
            binding.textViewDownloads.text = image?.downloads.toString()
            binding.textViewComments.text = image?.comments.toString()
            binding.textViewUserName.text = image?.user.toString()

            Glide.with(binding.imageViewUser)
                .load(image?.userImageURL)
                .circleCrop()
                .placeholder(R.drawable.image_placeholder)
                .into(binding.imageViewUser)
        })

        return view
    }

}