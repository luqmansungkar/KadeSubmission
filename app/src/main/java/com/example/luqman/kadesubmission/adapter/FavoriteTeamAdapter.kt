package com.example.luqman.kadesubmission.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.TeamDetailActivity
import com.example.luqman.kadesubmission.model.FavoriteTeam
import com.example.luqman.kadesubmission.ui.TeamRowUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteTeamAdapter(private val favorite: List<FavoriteTeam>) : RecyclerView.Adapter<FavoriteTeamViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamViewHolder {
        return FavoriteTeamViewHolder(
            TeamRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteTeamViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }
}

class FavoriteTeamViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.team_row_badge)
    private val teamName: TextView = view.find(R.id.team_row_name)

    fun bindItem(favorite: FavoriteTeam) {
        Picasso.get().load(favorite.teamBadge).into(teamBadge)
        teamName.text = favorite.teamName

        itemView.setOnClickListener {
            context.startActivity<TeamDetailActivity>("team_id" to favorite.teamId)
        }
    }
}