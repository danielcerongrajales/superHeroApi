package com.example.superheroapi.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapi.data.utils.DataState
import com.example.superheroapi.R
import com.example.superheroapi.databinding.FragmentHomeBinding
import com.example.superheroapi.domain.model.SuperHeroItem
import com.example.superheroapi.ui.adapters.SuperHeroAdapter
import com.example.superheroapi.ui.viewmodel.SuperHeroViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val movieViewModel: SuperHeroViewModel by viewModels()
    private lateinit var movieAdapter2: SuperHeroAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding.rvSuperHeros.apply {
            layoutManager = GridLayoutManager(this.context, 2)
            setHasFixedSize(true)

        }

        movieAdapter2 = SuperHeroAdapter { actionUI ->
            when (actionUI) {
                is ActionUI.Click -> {
                    val bundle = bundleOf("idSuperHero" to actionUI.superHeroItem)
                    this.findNavController()
                        .navigate(R.id.action_homeFragment_to_detailsFragment, bundle)

                }


            }
        }

        setObservers()
        return binding.root
    }


    private fun setObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
//            viewLifecycleOwner.lifecycle.whenCreated {
                movieViewModel.stateTwo.collect { uiStatedos ->
                    if (uiStatedos.loadingBar) {
                        binding.containerShimmerSuperHero.shimmer.startShimmer()
                        binding.containerShimmerSuperHero.shimmer.visibility = View.VISIBLE
                        binding.rvSuperHeros.visibility = View.GONE
                    } else {
                        binding.containerShimmerSuperHero.shimmer.stopShimmer()
                        binding.containerShimmerSuperHero.shimmer.visibility = View.GONE
                        binding.rvSuperHeros.visibility = View.VISIBLE

                    }


                    when (val it = uiStatedos.superHeros) {
                        is DataState.Data -> {
                            movieAdapter2.submitList(it.data)
                        }
                        is DataState.Error -> {

                        }
                        null -> {

                        }
                    }
                    binding.rvSuperHeros.adapter = movieAdapter2

                }

            }
        }

    }


}

sealed interface ActionUI {
    class Click(val superHeroItem: SuperHeroItem) : ActionUI
}