package com.example.luqman.kadesubmission.presenter

import android.widget.ImageView
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.model.TeamResponse
import com.example.luqman.kadesubmission.view.DetailView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchDetailPresenterTest {

    @Mock
    private lateinit var view: DetailView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    @Mock
    private lateinit var imageView: ImageView

    private lateinit var presenter: MatchDetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchDetailPresenter(view, apiRepository, gson, null, null)
    }

    @Test
    fun getTeamDetails() {
        val teams: MutableList<Team> = mutableListOf()
        val response = TeamResponse(teams)
        val teamId = "133814"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeamDetails(teamId)).await(), TeamResponse::class.java)).thenReturn(response)

            presenter.getTeamDetails(teamId, imageView)

            verify(view).showLoading()
            verify(view).hideLoading()
            verify(view).showTeamBadge(response.teams[0], imageView)
        }
    }

    @Test
    fun getMatchDetails() {
        val events: MutableList<Event> = mutableListOf()
        val response = MatchResponse(events)
        val matchId = "579231"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getMatchDetails(matchId)).await(), MatchResponse::class.java)).thenReturn(response)

            presenter.getMatchDetails(matchId)

            verify(view).showLoading()
            verify(view).hideLoading()
            verify(view).showMatchDetail(response.events[0])
        }
    }
}