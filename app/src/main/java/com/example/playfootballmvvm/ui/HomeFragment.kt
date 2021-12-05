package com.example.playfootballmvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.playfootballmvvm.R
import com.example.playfootballmvvm.adapters.FirstAdapter
import com.example.playfootballmvvm.adapters.HomeAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.databinding.FragmentHomeBinding
import com.example.playfootballmvvm.model.Standing
import com.example.playfootballmvvm.repository.Extra
import com.example.playfootballmvvm.repository.SecondRepository
import com.example.playfootballmvvm.repository.SortRepository
import com.example.playfootballmvvm.retrofit.ApiClient
import com.example.playfootballmvvm.retrofit.ApiClient2
import com.example.playfootballmvvm.utils.NetworkHelper
import com.example.playfootballmvvm.utils.SortLeagueResource
import com.example.playfootballmvvm.utils.hide
import com.example.playfootballmvvm.utils.show
import com.example.playfootballmvvm.viewmodel.ViewModelFactory
import com.example.playfootballmvvm.viewmodel.ViewModelFirst
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var viewModelFirst: ViewModelFirst
    private val TAG = "HomeFragment"
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.apply {

            viewModelFirst = ViewModelProvider(
                this@HomeFragment,
                ViewModelFactory(
                    SortRepository(ApiClient.apiService),
                    NetworkHelper(requireContext()),
                    SecondRepository(ApiClient2.apiService)
                )
            )[ViewModelFirst::class.java]



            lifecycleScope.launch {
                viewModelFirst.fetchLeague().collect {
                    when (it) {
                        is SortLeagueResource.Loading -> {
                            binding.progress.show()
                            binding.rv.hide()
                        }
                        is SortLeagueResource.Error -> {
                            binding.progress.hide()
                            binding.rv.hide()
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is SortLeagueResource.Success -> {
                            binding.progress.hide()
                            rv.show()
//                            firstAdapter = FirstAdapter(it.list?.data!!.standings,
//                                object : FirstAdapter.OnItemClick {
//                                    override fun click(standing: Standing) {
//                                        val bundle = Bundle()
//                                        bundle.putSerializable("standing", standing)
//                                        findNavController().navigate(R.id.infoFragment, bundle)
//                                    }
//                                })
//                            rv.adapter = firstAdapter

                            homeAdapter =
                                HomeAdapter(listener = object : HomeAdapter.OnItemClickListener {
                                    override fun onClick(league: League) {
                                        val bundle = Bundle()
                                        bundle.putSerializable("league", league)
                                        Extra.setLeague1(league)
                                        findNavController().navigate(R.id.infoFragment, bundle)
                                    }

                                }, context = requireContext(), leagues = loadData())

                            rv.adapter = homeAdapter
                        }
                    }
                }
            }


        }

        return binding.root
    }

    private fun loadData(): MutableList<League> {
        val list =
            listOf("arg.1", "aus.1", "bra.1", "eng.1", "fra.1", "ger.1", "ita.1")
        val leagueList = mutableListOf(

            League(
                id = "esp.1",
                name = "Spanish Primera División",
                ab = "La Liga",
                image = "https://a.espncdn.com/i/leaguelogos/soccer/500/15.png",
                tDivision = "PD"
            ), League(
                id = "eng.1",
                name = "English Premier League",
                ab = "Prem",
                tDivision = "PL",
                image = "https://a.espncdn.com/i/leaguelogos/soccer/500-dark/23.png"
            ),
            League(
                id = "fra.1",
                name = "French Ligue 1",
                ab = "Ligue 1",
                image = "https://a.espncdn.com/i/leaguelogos/soccer/500/9.png",
                tDivision = "FL1"
            ),
            League(
                id = "ger.1",
                name = "German Bundesliga",
                ab = "Bund",
                image = "https://a.espncdn.com/i/leaguelogos/soccer/500/10.png",
                tDivision = "BL1"
            ),
            League(
                id = "ita.1",
                name = "Italian Serie A",
                ab = "Serie A",
                image =
                "https://a.espncdn.com/i/leaguelogos/soccer/500-dark/12.png",
                tDivision = "SA"
            ),
            League(
                id = "ned.1",
                name = "Dutch Eredivisie",
                ab = "Erediv",
                image = "https://a.espncdn.com/i/leaguelogos/soccer/500/11.png",
                tDivision = "DED"
            )

        )
        return leagueList

    }

}