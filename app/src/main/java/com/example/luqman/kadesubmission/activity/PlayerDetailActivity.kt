package com.example.luqman.kadesubmission.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.api.ApiRepository
import com.example.luqman.kadesubmission.model.Player
import com.example.luqman.kadesubmission.presenter.PlayerDetailPresenter
import com.example.luqman.kadesubmission.util.EspressoIdlingResource
import com.example.luqman.kadesubmission.util.invisible
import com.example.luqman.kadesubmission.util.visible
import com.example.luqman.kadesubmission.view.PlayerDetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find

class PlayerDetailActivity : AppCompatActivity(), PlayerDetailView {

    private lateinit var playerId: String
    private lateinit var playerName: String
    private lateinit var presenter: PlayerDetailPresenter

    private lateinit var playerFanart: ImageView
    private lateinit var playerHeight: TextView
    private lateinit var playerWeight: TextView
    private lateinit var playerDesc: TextView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_detail)

        playerId = intent.getStringExtra("player_id")
        playerName = intent.getStringExtra("player_name")

        supportActionBar?.title = playerName
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        playerFanart = find(R.id.player_fanart)
        playerHeight = find(R.id.player_height)
        playerWeight = find(R.id.player_weight)
        playerDesc = find(R.id.player_desc)
        progressBar = find(R.id.player_detail_progress_bar)

        val request = ApiRepository()
        val gson = Gson()
        presenter = PlayerDetailPresenter(this, request, gson)

        presenter.getPlayerDetails(playerId)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showPlayerDetail(player: Player) {
        if (player.playerFanart != null){
            Picasso.get().load(player.playerFanart).into(playerFanart)
        }else{
            Picasso.get().load("http://scanyourentirelife.com/wp-content/uploads/2014/06/image-placeholder-1.jpeg").into(playerFanart)
        }
        if(player.playerHeight != null || player.playerHeight == ""){
            playerHeight.text = player.playerHeight
        }else{
            playerHeight.text = "N/A"
        }
        if(player.playerWeight != null || player.playerWeight == ""){
            playerWeight.text = player.playerWeight
        }else{
            playerWeight.text = "N/A"
        }
        playerDesc.text = player.playerDesc

        EspressoIdlingResource.decrement()
    }

}
