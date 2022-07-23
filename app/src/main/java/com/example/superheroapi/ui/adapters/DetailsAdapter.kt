package com.example.superheroapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.databinding.ItemDetailsBinding
import com.example.superheroapi.domain.model.DetailsItem


class DetailsAdapter() :
    ListAdapter<DetailsItem, DetailsAdapter.MovieHolder>(MovieComparator) {
    companion object {
        private val MovieComparator = object : DiffUtil.ItemCallback<DetailsItem>() {
            override fun areItemsTheSame(oldItem: DetailsItem, newItem: DetailsItem): Boolean {
                return (
                        oldItem.title == newItem.title)
            }

            override fun areContentsTheSame(oldItem: DetailsItem, newItem: DetailsItem): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            ItemDetailsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        getItem(position)?.let { holder.render(it) }
    }


    class MovieHolder(private val binding: ItemDetailsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun render(movie: DetailsItem) {
            binding.tvDetailsTitle.text = movie.title
            binding.tvDetailsContent.text = movie.value
        }
    }

}


