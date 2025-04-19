package com.example.pokeapi.presenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokeapi.databinding.PokemonListFrgBinding
import com.example.pokeapi.presenter.viewmodel.PokemonListViewModel


class PokemonListFrg : Fragment() {

    private val viewModel: PokemonListViewModel by viewModels()
    private lateinit var binding: PokemonListFrgBinding
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PokemonListFrgBinding.inflate(inflater,container,false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerListPoke.layoutManager = LinearLayoutManager(requireContext())
        binding.btnLoad.setOnClickListener{
            binding.btnLoad.isEnabled = false
            binding.progressLayout.visibility = View.VISIBLE
            binding.progressBar.progress = 0
            binding.progresText.text = "0%"
            viewModel.loadPoke()
        }

        viewModel.pokelist.observe(viewLifecycleOwner){ pokemonList ->

            adapter = PokemonAdapter(pokemonList){ selectedPokemon ->
                val action = PokemonListFrgDirections.pokemonListFrgToPokemonDetailFrg(selectedPokemon.name)
                findNavController().navigate(action)
            }
            binding.recyclerListPoke.adapter = adapter
        }
        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressLayout.visibility = if (isLoading) View.VISIBLE else View.GONE
            binding.btnLoad.isEnabled = !isLoading
        }
        viewModel.progressValue.observe(viewLifecycleOwner){ progress ->
            binding.progressBar.progress = progress
            binding.progresText.text = progress.toString() + "% Pokemons"
        }
        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error.toString(), Toast.LENGTH_LONG).show()
        }

    }
}