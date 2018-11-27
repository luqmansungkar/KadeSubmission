package com.example.luqman.kadesubmission.fragment


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar

import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.presenter.MainPresenter
import com.example.luqman.kadesubmission.view.MainView
import com.example.luqman.kadesubmission.adapter.MatchAdapter
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.ui.MatchListUI
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext

class NextMatchFragment : Fragment(), MainView {

    private var events: MutableList<Event> = mutableListOf()
    private lateinit var adapter: MatchAdapter
    private lateinit var presenter: MainPresenter
    private lateinit var progress: ProgressBar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        adapter = MatchAdapter(events)
        val view = MatchListUI(adapter).createView(AnkoContext.create(this@NextMatchFragment.context as Context, this))
//        progress = find(R.id.main_progress_bar)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        presenter.getNextMatchList("4331")

        return view
    }

    override fun showLoading() {
//        progress.visible()
    }

    override fun hideLoading() {
//        progress.invisible()
    }

    override fun showMatchList(data: List<Event>) {
        events.clear()
        events.addAll(data)
        adapter.notifyDataSetChanged()
        EspressoIdlingResource.decrement()
    }

}
