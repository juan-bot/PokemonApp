package com.example.pokeapi.presenter.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.pokeapi.R
import com.example.pokeapi.databinding.PokemonDetailFrgBinding
import com.example.pokeapi.presenter.viewmodel.PokemonDetailViewModel

class PokemonDetailFrg : Fragment() {
private val viewModel: PokemonDetailViewModel by viewModels()
    private lateinit var binding: PokemonDetailFrgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PokemonDetailFrgBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args = PokemonDetailFrgArgs.fromBundle(requireArguments())
        val nombre = args.pokemonName
        viewModel.loadPoke(nombre)
        viewModel.pokemon.observe(viewLifecycleOwner){ pokemon ->
            binding.pokemonName.text = pokemon.name
            binding.textWeight.text = pokemon.weight.toString()
            binding.textHeight.text = pokemon.height.toString()
            binding.textAbility.text = pokemon.abilities[0].ability.name
            Glide.with(view).load(pokemon.sprites.front_shiny).into(binding.pokemonImage)

        }
    }
}