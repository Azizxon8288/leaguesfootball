package com.example.playfootballmvvm.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.playfootballmvvm.R
import com.example.playfootballmvvm.adapters.ThreeAdapter
import com.example.playfootballmvvm.adapters.TopScoresAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.databinding.FragmentScoresBinding
import com.example.playfootballmvvm.repository.Extra
import com.example.playfootballmvvm.repository.SecondRepository
import com.example.playfootballmvvm.repository.SortRepository
import com.example.playfootballmvvm.retrofit.ApiClient
import com.example.playfootballmvvm.retrofit.ApiClient2
import com.example.playfootballmvvm.utils.*
import com.example.playfootballmvvm.viewmodel.ViewModelFactory
import com.example.playfootballmvvm.viewmodel.ViewModelFirst
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScoresFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoresFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: League? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getSerializable(ARG_PARAM1) as League?
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentScoresBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFirst: ViewModelFirst
    private lateinit var topScoresAdapter: TopScoresAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentScoresBinding.inflate(inflater, container, false)
        binding.apply {
            viewModelFirst = ViewModelProvider(
                this@ScoresFragment,
                ViewModelFactory(
                    SortRepository(ApiClient.apiService),
                    NetworkHelper(requireContext()),
                    SecondRepository(ApiClient2.apiService)
                )
            )[ViewModelFirst::class.java]


            lifecycleScope.launch {
                viewModelFirst.fetchLeagueTopScores(param1!!.tDivision).collect {
                    when (it) {
                        is TopScoresResource.Loading -> {
                            binding.progress.show()
                            binding.rv.hide()
                        }
                        is TopScoresResource.Error -> {
                            binding.progress.hide()
                            binding.rv.hide()
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is TopScoresResource.Success -> {
                            binding.progress.hide()
                            rv.show()
                            topScoresAdapter = TopScoresAdapter(it.body.scorers)
                            rv.adapter = topScoresAdapter

                        }
                    }
                }
            }
        }
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: League) =
            ScoresFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}