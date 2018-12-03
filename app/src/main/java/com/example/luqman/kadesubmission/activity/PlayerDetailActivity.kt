package com.example.luqman.kadesubmission.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Player
import com.example.luqman.kadesubmission.presenter.PlayerDetailPresenter
import com.example.luqman.kadesubmission.view.PlayerDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var playerId: String
    private lateinit var presenter: PlayerDetailPresenter
    private lateinit var player: Player

    private lateinit var playerFanart: ImageView
    private lateinit var playerHeight: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerDesc: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerId = intent.getStringExtra("player_id")

        playerFanart = find(R.id.player_fanart)
        playerHeight = find(R.id.player_height)
        playerWeight = find(R.id.player_weight)
        playerDesc = find(R.id.player_desc)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)

        presenter.getPlayerDetails(playerId)
    }

    override fun showLoading() {

    }

    override fun hideLoading() {
    }

    override fun showPlayerDetail(player: Player) {
        Picasso.get().load(player.playerFanart).into(playerFanart)
        playerHeight.text = player.playerHeight
        playerWeight.text = player.playerWeight
        playerDesc.text = player.playerDesc
    }

}
