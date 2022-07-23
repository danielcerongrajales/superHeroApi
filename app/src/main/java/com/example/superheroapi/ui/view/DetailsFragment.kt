package com.example.superheroapi.ui.view

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.superheroapi.databinding.DialogDetailsBinding
import com.example.superheroapi.databinding.FragmentDetailsBinding
import com.example.superheroapi.domain.model.DetailsItem
import com.example.superheroapi.domain.model.SuperHeroItem
import com.example.superheroapi.ui.adapters.DetailsAdapter
import com.example.superheroapi.ui.utils.loadCoil
import com.example.superheroapi.ui.viewmodel.DetailsViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailsFragment : Fragment() {
    private lateinit var dialogBinding: DialogDetailsBinding
    private lateinit var materialAlertDialogBuilder: MaterialAlertDialogBuilder
    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var movieAdapterCredits: DetailsAdapter
    private val list = mutableListOf<DetailsItem>()
    private val list2 = mutableListOf<DetailsItem>()


    private val detailsViewModel: DetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        setUpListeners()
        setUpObservers()
        val serializableDataClass = arguments?.getParcelable<Parcelable>("idSuperHero")
        val dataClass = serializableDataClass as SuperHeroItem
        binding.imageView2.loadCoil(dataClass.images.sm, "#1339C1")
        binding.imageView3.loadCoil(dataClass.images.sm, "#1339C1")
        materialAlertDialogBuilder = MaterialAlertDialogBuilder(requireContext())

        return binding.root
    }

    private fun setUpObservers() {
        detailsViewModel.powerStatsById.observe(viewLifecycleOwner) {
            changeTextViewState(binding.textVieww3, "Durabilidad: ${it.durability}")
            changeTextViewState(binding.textView4, "Poder: ${it.power}")
            changeTextViewState(binding.textView7, "Velocidad: ${it.speed}")
            changeTextViewState(binding.textView8, "Inteligencia: ${it.intelligence}")
            changeTextViewState(binding.textView9, "Combate: ${it.combat}")

        }
        detailsViewModel.appearanceById.observe(viewLifecycleOwner) {
            list.clear()
            list.add(DetailsItem("Ojos", it.eyeColor))
            list.add(DetailsItem("Cabello", it.hairColor))
            list.add(DetailsItem("Genero", it.gender))
            list.add(DetailsItem("Especie", it.race))
            val height = buildString { it.height.forEach(::append) }
            list.add(DetailsItem("Altura", height))
            val weight = buildString { it.weight.forEach(::append) }
            list.add(DetailsItem("Peso", weight))


        }

        detailsViewModel.biographyById.observe(viewLifecycleOwner) {
            list2.clear()
            list2.add(DetailsItem("Alter Egos", it.alterEgos))
            list2.add(DetailsItem("Nombre Completo", it.fullName))
            list2.add(DetailsItem("Genero", it.alignment))
            list2.add(DetailsItem("Primera Presentación", it.firstAppearance))
            list2.add(DetailsItem("lugar de Nacimiento", it.placeOfBirth))
            list2.add(DetailsItem("Editor", it.publisher))
            val aliases = buildString { it.aliases.forEach(::append) }

            list2.add(DetailsItem("Alias",aliases))

        }

        detailsViewModel.workById.observe(viewLifecycleOwner) {
            binding.txtTrabajoDescripcion.text = "Ocupación: ${it.occupation}"
            binding.txtTrabajoTitle.text = "Base: ${it.base}"
        }
        binding.shimmerad.shimmer.stopShimmer()
        binding.shimmerad.shimmer.visibility=GONE


    }

    private fun changeTextViewState(vista: TextView, textValor: String) {
        if (textValor.isNotBlank() && textValor.isNotEmpty()) {
            vista.text = textValor
            vista.visibility = VISIBLE
        }
    }

    private fun setUpListeners() {
        binding.btnApareincia.setOnClickListener {
            dialogBinding =
                DialogDetailsBinding.inflate(LayoutInflater.from(requireContext()), null, false)
            launchCustomAlertDialog("Apariencia", list)
        }
        binding.btnBiografia.setOnClickListener {
            dialogBinding =
                DialogDetailsBinding.inflate(LayoutInflater.from(requireContext()), null, false)

            launchCustomAlertDialog("Biografia", list2)
        }

    }

    private fun launchCustomAlertDialog(titulo: String, list: MutableList<DetailsItem>) {

        materialAlertDialogBuilder.setView(dialogBinding.root)
            .setTitle(titulo)
            .apply {
                movieAdapterCredits = DetailsAdapter()
                movieAdapterCredits.submitList(list)

                dialogBinding.recycler.apply {
                    layoutManager = GridLayoutManager(this.context, 2)
                    setHasFixedSize(true)
                }
                dialogBinding.recycler.adapter = movieAdapterCredits
            }
            .show()

    }

}