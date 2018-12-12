package com.example.luqman.kadesubmission.adapter

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.RecyclerView
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.MatchDetailActivity
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.ui.MatchRowUI
import com.example.luqman.kadesubmission.util.DateTimeUtil
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class MatchAdapter(private val events: List<Event>) :
    RecyclerView.Adapter<MatchViewHolder>(), Filterable {

    private var filteredEvent: List<Event> = events

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return filteredEvent.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(filteredEvent[position])
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val searchQuery = constraint.toString()
                if (searchQuery.isEmpty()) {
                    filteredEvent = events
                } else {
                    val filtered: MutableList<Event> = mutableListOf()
                    events.forEach {
                        var homeTeamName = it.homeTeam
                        homeTeamName = homeTeamName ?: ""

                        var awayTeamName = it.awayTeam
                        awayTeamName = awayTeamName ?: ""

                        if (homeTeamName.contains(searchQuery, true) || awayTeamName.contains(searchQuery, true)) {
                            filtered.add(it)
                        }
                    }
                    filteredEvent = filtered
                }
                val result = FilterResults()
                result.values = filteredEvent
                return result
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                filteredEvent = results?.values as List<Event>
                notifyDataSetChanged()
            }

        }
    }

}

class MatchViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val matchDate: TextView = view.find(R.id.match_date)
    private val matchTime: TextView = view.find(R.id.match_time)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val awayScore: TextView = view.find(R.id.away_score)

    fun bindItem(event: Event) {
        val sourceDateTimeString = event.matchDate + " " + event.matchTime
        val sourceDateTimeFormat = "yyyy-MM-dd HH:mm:ssZZ"

        val dateString: String =
            DateTimeUtil.formatDateTime(sourceDateTimeString, sourceDateTimeFormat, "EEE, dd MMM yyyy")
        val timeString: String = DateTimeUtil.formatDateTime(sourceDateTimeString, sourceDateTimeFormat, "HH:mm")

        val dateSpannable = SpannableStringBuilder(dateString)
        dateSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, dateString.length, 0)
        dateSpannable.setSpan(ForegroundColorSpan(Color.GRAY), 0, dateString.length, 0)
        matchDate.text = dateSpannable
        matchTime.text = timeString
        homeTeam.text = event.homeTeam

        val homeScoreText = if (event.homeScore == null) "" else event.homeScore.toString()
        homeScore.text = homeScoreText
        awayTeam.text = event.awayTeam
        val awayScoreText = if (event.awayScore == null) "" else event.awayScore.toString()
        awayScore.text = awayScoreText



        itemView.setOnClickListener {
            context.startActivity<MatchDetailActivity>("match_id" to event.eventId)
        }
    }
}