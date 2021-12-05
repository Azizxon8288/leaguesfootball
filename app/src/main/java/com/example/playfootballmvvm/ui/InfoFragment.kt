package com.example.playfootballmvvm.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.playfootballmvvm.MainActivity
import com.example.playfootballmvvm.R
import com.example.playfootballmvvm.adapters.FirstViewpagerAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.databinding.FragmentInfoBinding
import com.example.playfootballmvvm.utils.setImage
import com.google.android.material.tabs.TabLayoutMediator

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "league"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [InfoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InfoFragment : Fragment() {
    private var league: League? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            league = it.getSerializable("league") as League
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding: FragmentInfoBinding? = null
    private val binding get() = _binding!!
    private lateinit var firstViewpagerAdapter: FirstViewpagerAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInfoBinding.inflate(inflater, container, false)
        (activity as MainActivity).hide()
        binding.apply {
            backBtn.setOnClickListener {
                findNavController().popBackStack()
                (activity as MainActivity).show()
            }

            image.setImage(league!!.image)
            tvName.text = league!!.name


            viewpager.isUserInputEnabled = false
            firstViewpagerAdapter = FirstViewpagerAdapter(this@InfoFragment, league!!)
            viewpager.adapter = firstViewpagerAdapter

            TabLayoutMediator(tab, viewpager) { tab, position ->
                if (position == 0) {
                    tab.text = "Matches"
                } else {
                    tab.text = "Tables"
                }
            }.attach()
        }
        return binding.root
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(league: String, param2: String) =
            InfoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, league)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}