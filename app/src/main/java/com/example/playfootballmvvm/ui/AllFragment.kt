package com.example.playfootballmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.playfootballmvvm.R
import com.example.playfootballmvvm.adapters.FirstAdapter
import com.example.playfootballmvvm.adapters.ThreeAdapter
import com.example.playfootballmvvm.databinding.FragmentAllBinding
import com.example.playfootballmvvm.model.Standing
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
 * Use the [AllFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModelFirst: ViewModelFirst
    private lateinit var threeAdapter: ThreeAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(inflater, container, false)

        binding.apply {
            viewModelFirst = ViewModelProvider(
                this@AllFragment,
                ViewModelFactory(
                    SortRepository(ApiClient.apiService),
                    NetworkHelper(requireContext()),
                    SecondRepository(ApiClient2.apiService)
                )
            )[ViewModelFirst::class.java]


            lifecycleScope.launch {
                viewModelFirst.fetchTeamsByLeague(Extra.getLeague1()!!.id).collect {
                    when (it) {
                        is TeamResource.Loading -> {
                            binding.progress.show()
                            binding.rv.hide()
                        }
                        is TeamResource.Error -> {
                            binding.progress.hide()
                            binding.rv.hide()
                            Toast.makeText(requireContext(), "${it.message}", Toast.LENGTH_SHORT)
                                .show()
                        }
                        is TeamResource.Success -> {
                            binding.progress.hide()
                            rv.show()
                            threeAdapter = ThreeAdapter(it.body.data.standings)
                            rv.adapter = threeAdapter

                        }
                    }
                }
            }

        }

        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AllFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AllFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}