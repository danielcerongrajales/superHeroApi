package com.example.superheroapi.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.superheroapi.databinding.ItemSuperheroBinding
import com.example.superheroapi.ui.utils.loadCoil
import com.example.superheroapi.ui.view.ActionUI
import com.example.superheroapi.ui.viewmodel.SuperHeroItemUiState


class SuperHeroAdapter(private val listener: (ActionUI) -> Unit) :
    ListAdapter<SuperHeroItemUiState, SuperHeroAdapter.MovieHolder>(SuperHeroComparator) {
    companion object {
        private val SuperHeroComparator = object : DiffUtil.ItemCallback<SuperHeroItemUiState>() {
            override fun areItemsTheSame(
                oldItem: SuperHeroItemUiState,
                newItem: SuperHeroItemUiState
            ): Boolean {
                return (
                        oldItem.superHeroItem.id == newItem.superHeroItem.id)
            }

            override fun areContentsTheSame(
                oldItem: SuperHeroItemUiState,
                newItem: SuperHeroItemUiState
            ): Boolean =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val binding =
            ItemSuperheroBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {

        getItem(position)?.let { holder.render(it, listener) }
    }


    class MovieHolder(private val binding: ItemSuperheroBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun render(superHero: SuperHeroItemUiState, listener: (ActionUI) -> Unit) {
            binding.textView.text = superHero.superHeroItem.name
            superHero.superHeroItem.images.sm.let {
                binding.imageView.loadCoil(it, "#850914")
            }
            binding.root.setOnClickListener { listener(ActionUI.Click(superHero.superHeroItem)) }

        }

    }


}


