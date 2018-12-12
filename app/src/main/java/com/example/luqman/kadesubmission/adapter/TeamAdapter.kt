package com.example.luqman.kadesubmission.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.TeamDetailActivity
import com.example.luqman.kadesubmission.model.Team
import com.example.luqman.kadesubmission.ui.TeamRowUI
import com.squareup.picasso.Picasso
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class TeamAdapter(private var teams: List<Team>) : RecyclerView.Adapter<TeamViewHolder>(), Filterable {

    private var filteredTeams: List<Team> = teams

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        return TeamViewHolder(
            TeamRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return filteredTeams.size
    }

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        holder.bindItem(filteredTeams[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {

            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchQuery = constraint.toString()
                if (searchQuery.isEmpty()) {
                    filteredTeams = teams
                } else {
                    val teamFiltered: MutableList<Team> = mutableListOf()
                    teams.forEach {
                        var teamName = it.teamName
                        teamName = teamName ?: ""

                        if (teamName.contains(searchQuery, true)) {
                            teamFiltered.add(it)
                        }
                    }
                    filteredTeams = teamFiltered
                }
                val result = FilterResults()
                result.values = filteredTeams
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredTeams = results?.values as List<Team>
                notifyDataSetChanged()
            }

        }
    }

}

class TeamViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val teamBadge: ImageView = view.find(R.id.team_row_badge)
    private val teamName: TextView = view.find(R.id.team_row_name)

    fun bindItem(teams: Team) {
        Picasso.get().load(teams.teamBadge).into(teamBadge)
        teamName.text = teams.teamName

        itemView.setOnClickListener {
            context.startActivity<TeamDetailActivity>("team_id" to teams.teamId)
        }
    }
}