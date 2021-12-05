package com.example.playfootballmvvm.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.playfootballmvvm.adapters.SecondViewpagerAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.databinding.FragmentTableBinding
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TableFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TableFragment : Fragment() {
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

    private var _binding: FragmentTableBinding? = null
    private val binding get() = _binding!!
    private val TAG = "TableFragment"
    private lateinit var secondViewpagerAdapter: SecondViewpagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTableBinding.inflate(inflater, container, false)
        binding.apply {
            secondViewpagerAdapter = SecondViewpagerAdapter(this@TableFragment, param1!!)
            viewpager.adapter = secondViewpagerAdapter

            Log.d(TAG, "onCreateView: $param1")
            TabLayoutMediator(tab, viewpager) { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "All"
                    }
                    1 -> {
                        tab.text = "Score"
                    }
                }
            }.attach()
        }
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: League) =
            TableFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PARAM1, param1)
                }
            }
    }
}