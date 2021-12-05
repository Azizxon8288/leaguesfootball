package com.example.playfootballmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playfootballmvvm.databinding.ItemScoresBinding
import com.example.playfootballmvvm.model.topscores.Scorer

class TopScoresAdapter(var list: List<Scorer>) :
    RecyclerView.Adapter<TopScoresAdapter.MyViewHolder>() {

    inner class MyViewHolder(var itemScoresBinding: ItemScoresBinding) :
        RecyclerView.ViewHolder(itemScoresBinding.root) {
        fun onBind(scorer: Scorer, position: Int) {
            itemScoresBinding.apply {
                rankTv.text = "${position + 1}"
                tvName.text = scorer.player.name
                countTv.text = "${scorer.numberOfGoals}"
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemScoresBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size
}