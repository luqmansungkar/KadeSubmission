package com.example.luqman.kadesubmission.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.PlayerDetailActivity
import com.example.luqman.kadesubmission.model.Player
import com.example.luqman.kadesubmission.ui.PlayerRowUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class PlayerAdapter(private val players: List<Player>) : RecyclerView.Adapter<PlayerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        return PlayerViewHolder(
            PlayerRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return players.size
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bindItem(players[position])
    }

}

class PlayerViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val playerCutout: ImageView = view.find(R.id.player_row_cutout)
    private val playerName: TextView = view.find(R.id.player_row_name)
    private val playerPosition: TextView = view.find(R.id.player_row_position)

    fun bindItem(player: Player) {
        when {
            player.playerCutout != null -> Picasso.get().load(player.playerCutout).into(playerCutout)
            player.playerThumb != null -> Picasso.get().load(player.playerThumb).into(playerCutout)
            else -> Picasso.get().load("https://style.anu.edu.au/_anu/4/images/placeholders/person.png").into(
                playerCutout
            )
        }
        playerName.text = player.playerName
        playerPosition.text = player.playerPosition

        itemView.setOnClickListener {
            context.startActivity<PlayerDetailActivity>(
                "player_id" to player.playerId,
                "player_name" to player.playerName
            )
        }
    }
}