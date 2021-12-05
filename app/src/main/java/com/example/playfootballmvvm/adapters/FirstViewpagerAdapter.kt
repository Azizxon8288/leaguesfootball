package com.example.playfootballmvvm.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.ui.MatchesFragment
import com.example.playfootballmvvm.ui.TableFragment

class FirstViewpagerAdapter(fragment: Fragment, var league: League) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) {
            MatchesFragment()
        } else {
            TableFragment.newInstance(league)
        }
    }
}