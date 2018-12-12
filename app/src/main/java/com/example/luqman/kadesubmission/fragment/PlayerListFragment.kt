package com.example.luqman.kadesubmission.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.adapter.PlayerAdapter
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Player
import com.example.luqman.kadesubmission.presenter.PlayerListPresenter
import com.example.luqman.kadesubmission.ui.PlayerListUI
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
import com.example.luqman.kadesubmission.view.PlayerListView
import com.google.gson.Gson
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.support.v4.find

class PlayerListFragment : Fragment(), PlayerListView {

    private var players: MutableList<Player> = mutableListOf()

    private lateinit var adapter: PlayerAdapter
    private lateinit var presenter: PlayerListPresenter
    private lateinit var progressBar: ProgressBar
    private lateinit var empty: LinearLayout
    private lateinit var list: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        adapter = PlayerAdapter(players)
        val view =
            PlayerListUI(adapter).createView(AnkoContext.create(this@PlayerListFragment.context as Context, this))

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerListPresenter(this, request, gson)

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val teamId: String = if (arguments?.get("team_id") != null) arguments?.get("team_id").toString() else "0"

        progressBar = find(R.id.list_player_progress_bar)
        empty = find(R.id.player_empty_view)
        list = find(R.id.list_player)
        presenter.getPlayerList(teamId)

    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerList(players: List<Player>?) {
        this.players.clear()
        if (players != null) {
            this.players.addAll(players)

            if (players.isEmpty()) {
                empty.visible()
                list.invisible()
            } else {
                empty.invisible()
                list.visible()
            }
        }
        adapter.notifyDataSetChanged()
        EspressoIdlingResource.decrement()
    }

}