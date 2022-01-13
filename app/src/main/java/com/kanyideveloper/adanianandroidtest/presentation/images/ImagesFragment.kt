package com.kanyideveloper.adanianandroidtest.presentation.images

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.kanyideveloper.adanianandroidtest.databinding.FragmentImagesBinding
import com.kanyideveloper.adanianandroidtest.util.Resource
import com.kanyideveloper.adanianandroidtest.util.hideKeyboard
import com.kanyideveloper.adanianandroidtest.util.showSnackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect


@AndroidEntryPoint
class ImagesFragment : Fragment() {

    private val viewModel: ImagesViewModel by viewModels()
    private lateinit var adapter: ImagesAdapter
    private lateinit var binding: FragmentImagesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.searchQuery.value?.let { setUpObserver(it) }

        adapter = ImagesAdapter(ImagesAdapter.OnClickListener { image ->
            val action = ImagesFragmentDirections.actionImagesFragmentToImageDetailsFragment(image)
            findNavController().navigate(action)
        })

        binding.searchView.setEndIconOnClickListener {
            setUpObserver(binding.searchView.editText!!.text.toString())
            binding.imagesProgressBar.isVisible = true
            hideKeyboard()
        }

        return view
    }

    private fun setUpObserver(searchString: String) {
        viewLifecycleOwner.lifecycleScope.launchWhenStarted {
            viewModel.onSearch(searchString.trim()).collect { result ->
                when (result) {
                    is Resource.Success -> {
                        binding.imagesProgressBar.isVisible = false
                        binding.imagesRecyclerview.isVisible = true

                        if (result.data?.isEmpty()!!) {
                            showSnackbar("Nothing Found, Try Again!")
                        } else {
                            adapter.submitList(result.data)
                            binding.imagesRecyclerview.adapter = adapter
                        }
                    }

                    is Resource.Error -> {
                        binding.imagesProgressBar.isVisible = true
                        binding.imagesRecyclerview.isVisible = false
                        showSnackbar(result.message ?: "Unknown Error!")
                    }

                    is Resource.Loading -> {
                        binding.imagesProgressBar.isVisible = true
                    }
                }
            }
        }
    }
}