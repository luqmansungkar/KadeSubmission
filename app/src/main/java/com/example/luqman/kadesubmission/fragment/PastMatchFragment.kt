package com.example.luqman.kadesubmission.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Spinner
import com.example.luqman.kadesubmission.R

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.presenter.MatchPresenter
import com.example.luqman.kadesubmission.view.MatchView
import com.example.luqman.kadesubmission.adapter.MatchAdapter
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.model.Leagues
import com.example.luqman.kadesubmission.ui.MatchListUI
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find

class PastMatchFragment : Fragment(), MatchView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MatchPresenter
    private lateinit var progress: ProgressBar
    private lateinit var spinner: Spinner
    private var leagues: MutableList<Leagues> = mutableListOf()
    private var leagueMap: HashMap<Int, String?> = HashMap()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = MatchAdapter(events)
        val view = MatchListUI(adapter).createView(AnkoContext.create(this@PastMatchFragment.context as Context, this))

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        progress = find(R.id.main_progress_bar)
        presenter.getLeagueList()

    }

    override fun showLoading() {
        progress.visible()
    }

    override fun hideLoading() {
        progress.invisible()
    }

    override fun showLeagueList(leagues: List<Leagues>) {
        this.leagues.clear()
        this.leagues.addAll(leagues)

        spinner = find(R.id.league_spinner)

        val leagueArray = arrayOfNulls<String>(leagues.size)
        for(index in leagues.indices){
            leagueArray[index] = leagues[index].leagueName
            leagueMap[index] = leagues[index].leagueId
        }

        val spinnerAdapter = ArrayAdapter(this@PastMatchFragment.context as Context, android.R.layout.simple_spinner_dropdown_item, leagueArray)

        spinner.adapter = spinnerAdapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                presenter.getPastMatchList(leagueMap[position])
            }
        }

        EspressoIdlingResource.decrement()
    }

    override fun showMatchList(data: List<Event>?) {
        events.clear()
        if(data != null){
            events.addAll(data)
        }
        adapter.notifyDataSetChanged()
        EspressoIdlingResource.decrement()
    }

}
