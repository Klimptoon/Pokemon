package com.example.pokemon.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokemon.R
import com.example.pokemon.app.App
import com.example.pokemon.data.network.PokemonListPageSource
import com.example.pokemon.data.network.responses.Result
import com.example.pokemon.databinding.FragmentMainBinding
import javax.inject.Inject


class MainFragment : Fragment(), PokemonAdapterListener {


    @Inject
    lateinit var vmFactory: MainViewModelFactory
    lateinit var viewModel: MainFragmentViewModel
    private lateinit var adapter: MainAdapter
    private lateinit var binding: FragmentMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as App).appComponent.inject(this)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMainBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this, vmFactory)
            .get(MainFragmentViewModel::class.java)
        adapter = MainAdapter(this)
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rv.adapter = adapter


        viewModel.getPokemonList()


        viewModel.pokemonList.observe(viewLifecycleOwner, Observer {
            Log.d("ff", it.toString())
            adapter.submitData(lifecycle, it)

        })



        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

    }

    override fun onClickPokemon(result: Result) {
        val bundle = Bundle()
        bundle.putString("MyArg", result.name)
        findNavController().navigate(R.id.action_mainFragment_to_courseFragment, bundle)
        Log.d("ff", "жмяк жмяк ${result.url} ${result.name}")
    }


}