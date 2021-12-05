package com.example.playfootballmvvm.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.playfootballmvvm.repository.SecondRepository
import com.example.playfootballmvvm.repository.SortRepository
import com.example.playfootballmvvm.utils.NetworkHelper
import com.example.playfootballmvvm.utils.SortLeagueResource
import com.example.playfootballmvvm.utils.TeamResource
import com.example.playfootballmvvm.utils.TopScoresResource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class ViewModelFirst(
    private val sortRepository: SortRepository,
    private val networkHelper: NetworkHelper,
    private val secondRepository: SecondRepository

) : ViewModel() {


    fun fetchLeague(): StateFlow<SortLeagueResource> {
        val stateFlow = MutableStateFlow<SortLeagueResource>(SortLeagueResource.Loading)
        viewModelScope.launch {
            if (networkHelper.isNetworkConnected()) {
                sortRepository.getLeague().catch {
                    stateFlow.value = SortLeagueResource.Error(it.message ?: "")
                }.collect {
                    if (it.isSuccessful) {
                        val body = it.body()
                        stateFlow.emit(SortLeagueResource.Success(body))
                    } else {
                        when {
                            it.code() in 400..499 -> {
                                stateFlow.emit(SortLeagueResource.Error("Client error"))
                            }
                            it.code() in 500..599 -> {
                                stateFlow.emit(SortLeagueResource.Error("Server error"))
                            }
                            else -> {
                                stateFlow.emit(SortLeagueResource.Error("Other error"))
                            }
                        }
                    }
                }
            }
        }
        return stateFlow
    }

    fun fetchTeamsByLeague(id: String): MutableStateFlow<TeamResource> {
        val stateFlow = MutableStateFlow<TeamResource>(TeamResource.Loading)
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                sortRepository.getTeamsByLeague(id).catch {
                    stateFlow.emit(TeamResource.Error("${it.message} \n ${it.cause}"))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(TeamResource.Success(it.body()!!))
                    } else {
                        when {
                            it.code() in 400..499 -> {
                                stateFlow.emit(TeamResource.Error("Client error"))
                            }
                            it.code() in 500..599 -> {
                                stateFlow.emit(TeamResource.Error("Server error"))
                            }
                            else -> {
                                stateFlow.emit(TeamResource.Error("Other error"))
                            }
                        }
                    }
                }
            }

        }
        return stateFlow
    }

    fun fetchLeagueTopScores(str: String): MutableStateFlow<TopScoresResource> {
        val stateFlow = MutableStateFlow<TopScoresResource>(TopScoresResource.Loading)
        if (networkHelper.isNetworkConnected()) {
            viewModelScope.launch {
                secondRepository.getLeagueTopScores(str).catch {
                    stateFlow.emit(TopScoresResource.Error("${it.message} \n ${it.cause}"))
                }.collect {
                    if (it.isSuccessful) {
                        stateFlow.emit(TopScoresResource.Success(it.body()!!))
                    } else {
                        when {
                            it.code() in 400..499 -> {
                                stateFlow.emit(TopScoresResource.Error("Client error"))
                            }
                            it.code() in 500..599 -> {
                                stateFlow.emit(TopScoresResource.Error("Server error"))
                            }
                            else -> {
                                stateFlow.emit(TopScoresResource.Error("Other error"))
                            }
                        }
                    }
                }
            }

        }
        return stateFlow
    }


}