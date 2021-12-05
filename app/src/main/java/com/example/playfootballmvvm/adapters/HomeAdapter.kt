package com.example.playfootballmvvm.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.playfootballmvvm.database.entity.League
import com.example.playfootballmvvm.databinding.RvItemBinding
import com.example.playfootballmvvm.repository.SecondRepository
import com.example.playfootballmvvm.repository.SortRepository
import com.example.playfootballmvvm.retrofit.ApiClient
import com.example.playfootballmvvm.retrofit.ApiClient2
import com.example.playfootballmvvm.utils.NetworkHelper
import com.example.playfootballmvvm.utils.TeamResource
import com.example.playfootballmvvm.viewmodel.ViewModelFactory
import com.example.playfootballmvvm.viewmodel.ViewModelFirst
import com.squareup.picasso.Picasso
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class HomeAdapter(
    val context: Context,
    private var leagues: List<League>,
    val listener: OnItemClickListener
) :

    RecyclerView.Adapter<HomeAdapter.ViewHolder>() {
    private lateinit var viewModelFirst: ViewModelFirst

    class ViewHolder(var binding: RvItemBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            RvItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.apply {
            teamName.text = leagues[position].name
            Picasso.get().load(leagues[position].image).into(teamImage)
            bindChildView(leagues[position].id, holder.binding, position)

            enter.setOnClickListener {
                listener.onClick(leagues[position])
            }
        }


    }

    override fun getItemCount() = leagues.size


    interface OnItemClickListener {
        fun onClick(league: League)
    }

    private fun bindChildView(id: String, binding: RvItemBinding, position: Int) {
        viewModelFirst = ViewModelProvider(
            context as FragmentActivity,
            ViewModelFactory(
                SortRepository(ApiClient.apiService),
                NetworkHelper(context),
                SecondRepository(ApiClient2.apiService)
            )
        )[ViewModelFirst::class.java]
        context.lifecycleScope.launch {
            viewModelFirst.fetchTeamsByLeague(id).collect {
                when (it) {
                    is TeamResource.Error -> {

                    }
                    is TeamResource.Loading -> {
                        binding.apply {
                            lottie.visibility = View.VISIBLE
                            constraint.visibility = View.GONE
                        }

                    }
                    is TeamResource.Success -> {

                        binding.apply {
                            lottie.visibility = View.GONE
                            constraint.visibility = View.VISIBLE
                            country.text = leagues[position].ab
//                            team1Name.text = it.body.data.standings[0].team.abbreviation
//                            team2Name.text = it.body.data.standings[1].team.abbreviation
//                            team3Name.text = it.body.data.standings[2].team.abbreviation
//                            team4Name.text = it.body.data.standings[3].team.abbreviation
//                            team5Name.text = it.body.data.standings[4].team.abbreviation
                            team1ScoreDec.text = it.body.data.standings[0].team.name
                            team2ScoreDec.text = it.body.data.standings[1].team.name
                            team3ScoreDec.text = it.body.data.standings[2].team.name
                            team4ScoreDec.text = it.body.data.standings[3].team.name
                            team5ScoreDec.text = it.body.data.standings[4].team.name
                            team1Score.text = it.body.data.standings[0].stats[3].displayValue
                            team2Score.text = it.body.data.standings[1].stats[3].displayValue
                            team3Score.text = it.body.data.standings[2].stats[3].displayValue
                            team4Score.text = it.body.data.standings[3].stats[3].displayValue
                            team5Score.text = it.body.data.standings[4].stats[3].displayValue
                            team21Score.text = it.body.data.standings[0].stats[0].displayValue
                            team22Score.text = it.body.data.standings[1].stats[0].displayValue
                            team23Score.text = it.body.data.standings[2].stats[0].displayValue
                            team24Score.text = it.body.data.standings[3].stats[0].displayValue
                            team25Score.text = it.body.data.standings[4].stats[0].displayValue
                            team31Score.text = it.body.data.standings[0].stats[2].displayValue
                            team32Score.text = it.body.data.standings[1].stats[2].displayValue
                            team33Score.text = it.body.data.standings[2].stats[2].displayValue
                            team34Score.text = it.body.data.standings[3].stats[2].displayValue
                            team35Score.text = it.body.data.standings[4].stats[2].displayValue
                            team41Score.text = it.body.data.standings[0].stats[1].displayValue
                            team42Score.text = it.body.data.standings[1].stats[1].displayValue
                            team43Score.text = it.body.data.standings[2].stats[1].displayValue
                            team44Score.text = it.body.data.standings[3].stats[1].displayValue
                            team45Score.text = it.body.data.standings[4].stats[1].displayValue
                            team51Score.text = it.body.data.standings[0].stats[6].displayValue
                            team52Score.text = it.body.data.standings[1].stats[6].displayValue
                            team53Score.text = it.body.data.standings[2].stats[6].displayValue
                            team54Score.text = it.body.data.standings[3].stats[6].displayValue
                            team55Score.text = it.body.data.standings[4].stats[6].displayValue
                        }

                        Picasso.get().load(it.body.data.standings[0].team.logos[0].href)
                            .into(binding.team1Image)
                        Picasso.get().load(it.body.data.standings[1].team.logos[0].href)
                            .into(binding.team2Image)
                        Picasso.get().load(it.body.data.standings[2].team.logos[0].href)
                            .into(binding.team3Image)
                        Picasso.get().load(it.body.data.standings[3].team.logos[0].href)
                            .into(binding.team4Image)
                        Picasso.get().load(it.body.data.standings[4].team.logos[0].href)
                            .into(binding.team5Image)


                    }
                }
            }
        }

    }
}
