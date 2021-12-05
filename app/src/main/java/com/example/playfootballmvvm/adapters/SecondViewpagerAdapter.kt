package com.example.playfootballmvvm.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.ui.AllFragment
import com.example.playfootballmvvm.ui.ScoresFragment
import com.example.playfootballmvvm.ui.TableFragment

class SecondViewpagerAdapter(fragment: Fragment, var league: League) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                AllFragment()
            }
            else -> {
                ScoresFragment.newInstance(league)
            }
        }
    }
}