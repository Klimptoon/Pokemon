package com.example.pokemon.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.example.pokemon.R
import com.example.pokemon.app.App
import com.example.pokemon.databinding.FragmentPokemonInfoBinding
import com.example.pokemon.util.Constants.BUNDLE_KEY
import javax.inject.Inject

class PokemonInfoFragment : Fragment() {

    @Inject
    lateinit var vmFactory: PokemonInfoViewModelFactory
    lateinit var viewModel: PokemonInfoViewModel
    private lateinit var binding: FragmentPokemonInfoBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonInfoBinding.inflate(layoutInflater, container, false)
        viewModel = ViewModelProvider(this, vmFactory)[PokemonInfoViewModel::class.java]
        binding.returnButton.setOnClickListener {
            navController.navigate(R.id.action_courseFragment_to_mainFragment)
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        navController = Navigation.findNavController(requireView())
        init()
    }

    private fun init() {
        val pokemonName = arguments?.getString(BUNDLE_KEY)
        pokemonName?.let { viewModel.getPokemonInfo(it) }
        viewModel.pokemonInfo.observe(viewLifecycleOwner) { pokemon ->
            with(binding) {
                tvWeightNumber.text = getString(
                    R.string.weight_format,
                    pokemon.weight
                )
                tvHeightNumber.text = getString(
                    R.string.height_format,
                    pokemon.height
                )
                tvPokemonName.text = pokemon.pokemonName
                tvPokemonType.text = pokemon.type.toString()
                Glide.with(requireView())
                    .load(pokemon.image)
                    .into(ivPokemonImage)
            }
        }

        viewModel.isConnected.observe(viewLifecycleOwner) {
            if (it == false) {
                binding.tryButton.visibility = View.VISIBLE
                binding.tvWhenNoConnection.visibility = View.VISIBLE
                binding.returnButton.visibility = View.GONE
                binding.tryButton.setOnClickListener {
                    pokemonName?.let { viewModel.getPokemonInfo(pokemonName) }
                }
            } else {
                binding.tryButton.visibility = View.GONE
                binding.tvWhenNoConnection.visibility = View.GONE
                binding.returnButton.visibility = View.VISIBLE
            }
        }
    }
}
