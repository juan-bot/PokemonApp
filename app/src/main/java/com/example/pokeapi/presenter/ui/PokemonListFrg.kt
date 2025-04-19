package com.example.pokeapi.presenter.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokeapi.R
import com.example.pokeapi.databinding.PokemonListFrgBinding
import com.example.pokeapi.domain.model.Pokemon
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
            viewModel.loadPoke()
        }
        viewModel.adpList.observe(viewLifecycleOwner){
            adapter = it
            binding.recyclerListPoke.adapter = it

        }
    }
}