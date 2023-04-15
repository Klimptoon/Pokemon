package com.example.pokemon.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.pokemon.R
import com.example.pokemon.app.App
import com.example.pokemon.data.network.responses.Result
import com.example.pokemon.databinding.FragmentMainBinding
import com.example.pokemon.util.Constants.BUNDLE_KEY
import com.example.pokemon.util.Constants.COLUMNS
import javax.inject.Inject

class MainFragment : Fragment(), PokemonAdapterListener {

    @Inject
    lateinit var vmFactory: MainViewModelFactory
    private lateinit var viewModel: MainFragmentViewModel
    private lateinit var adapter: PokemonListAdapter
    private lateinit var binding: FragmentMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this, vmFactory)[MainFragmentViewModel::class.java]
        adapter = PokemonListAdapter(this)
        binding.pokemonRecyclerView.layoutManager = GridLayoutManager(requireContext(), COLUMNS)
        binding.pokemonRecyclerView.adapter = adapter
        viewModel.getPokemonList()
        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            adapter.submitData(lifecycle, it)
        })
        return binding.root
    }

    override fun onClickPokemon(result: Result) {
        val bundle = Bundle()
        bundle.putString(BUNDLE_KEY, result.name)
        findNavController().navigate(R.id.action_mainFragment_to_courseFragment, bundle)
    }
}
