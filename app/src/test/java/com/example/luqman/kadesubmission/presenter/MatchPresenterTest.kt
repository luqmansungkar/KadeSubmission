package com.example.luqman.kadesubmission.presenter

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.api.TheSportDBApi
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.MatchResponse
import com.example.luqman.kadesubmission.view.MatchView
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Test

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.Mockito.*

class MatchPresenterTest {

    @Mock
    private lateinit var view: MatchView

    @Mock
    private lateinit var gson: Gson

    @Mock
    private lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson)
    }

    @Test
    fun getPastMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = MatchResponse(events)
        val leagueId = "4331"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPastMatches(leagueId)).await(), MatchResponse::class.java)).thenReturn(response)

            presenter.getPastMatchList(leagueId)

            verify(view).showLoading()
            verify(view).showMatchList(response.events)
            verify(view).hideLoading()
        }
    }

    @Test
    fun getNextMatchList() {
        val events: MutableList<Event> = mutableListOf()
        val response = MatchResponse(events)
        val leagueId = "4331"

        GlobalScope.launch {
            `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getNextMatches(leagueId)).await(), MatchResponse::class.java)).thenReturn(response)

            presenter.getNextMatchList(leagueId)

            verify(view).showLoading()
            verify(view).showMatchList(response.events)
            verify(view).hideLoading()
        }
    }
}