package com.example.playfootballmvvm.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.playfootballmvvm.databinding.ItemSecondBinding
import com.example.playfootballmvvm.model.Standing
import com.example.playfootballmvvm.model.Team
import com.example.playfootballmvvm.utils.setImage

class SecondAdapter(var list: List<Standing>) :
    RecyclerView.Adapter<SecondAdapter.MySecondHolder>() {


    inner class MySecondHolder(var itemSecondBinding: ItemSecondBinding) :
        RecyclerView.ViewHolder(itemSecondBinding.root) {
        fun onBind(list: List<Standing>, position: Int) {
            itemSecondBinding.apply {
                image.setImage(list[position].team.logos[0].href)
                sa.text = list[position].team.shortDisplayName
                pTv.text = list[position].stats[3].displayValue
                dTv.text = list[position].stats[0].displayValue
                lTv.text = list[position].stats[2].displayValue
                gdTv.text = list[position].stats[1].displayValue
                pointTv.text = list[position].stats[6].displayValue
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MySecondHolder {
        return MySecondHolder(
            ItemSecondBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MySecondHolder, position: Int) {
        holder.onBind(list, position)
    }

    override fun getItemCount(): Int = 4
}