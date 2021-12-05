package com.example.playfootballmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.playfootballmvvm.databinding.ItemFirstBinding
import com.example.playfootballmvvm.model.Standing
import com.example.playfootballmvvm.model.Team
import com.example.playfootballmvvm.utils.setImage

class FirstAdapter(var list: List<Standing>, var listener: OnItemClick) :
    RecyclerView.Adapter<FirstAdapter.MyHolder>() {


    //    class MyDiffUtil() : DiffUtil.ItemCallback<List<Standing>>() {
//        override fun areItemsTheSame(oldItem: List<Standing>, newItem: List<Standing>): Boolean {
//            return oldItem[0].team.id==newItem[0].team.id
//        }
//
//        override fun areContentsTheSame(oldItem: List<Standing>, newItem: List<Standing>): Boolean {
//            return oldItem[0].team.id==newItem[0].team.id
//        }
//    }
    private lateinit var secondAdapter: SecondAdapter

    inner class MyHolder(var itemFirstBinding: ItemFirstBinding) :
        RecyclerView.ViewHolder(itemFirstBinding.root) {
        fun onBind(standing: Standing) {
            itemFirstBinding.apply {
                image1view.setImage("https://a.espncdn.com/i/leaguelogos/soccer/500/15.png")
                allBtn.setOnClickListener {
                    listener.click(standing)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemFirstBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.onBind(list[position])
//        val arr = ArrayList<Team>()
//        arr.add(list[0].team)
//        arr.add(list[1].team)
//        arr.add(list[2].team)
//        arr.add(list[3].team)

        secondAdapter = SecondAdapter(list)
        holder.itemFirstBinding.rv.adapter = secondAdapter

    }

    override fun getItemCount(): Int = 4

    interface OnItemClick {
        fun click(standing: Standing)

    }
}