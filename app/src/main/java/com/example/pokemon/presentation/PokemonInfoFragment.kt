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
        (activity?.applicationContext as App).appComponent.inject2(this)
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
        viewModel.pokemonInfo.observe(viewLifecycleOwner) {
            with(binding) {
                val weightString = getString(R.string.weight_format, it.weight)
                val heightString = getString(R.string.height_format, it.height)
                tvWeightNumber.text = weightString
                tvHeightNumber.text = heightString
                tvPokemonName.text = it.pokemonName
                tvPokemonType.text = it.type.toString()
                Glide.with(requireView())
                    .load(it.image)
                    .into(ivPokemonImage)
            }
        }

        viewModel.isConnected.observe(viewLifecycleOwner) {
            if (it == false) {
                binding.tryButton.visibility = View.VISIBLE
                binding.tvWhenNoConnection.visibility = View.VISIBLE
                binding.returnButton.visibility = View.GONE
                binding.tryButton.setOnClickListener {
                    pokemonName?.let { it1 -> viewModel.getPokemonInfo(it1) }
                }
            } else {
                binding.tryButton.visibility = View.GONE
                binding.tvWhenNoConnection.visibility = View.GONE
                binding.returnButton.visibility = View.VISIBLE
            }
        }
    }

}