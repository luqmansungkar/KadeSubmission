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
import android.widget.TextView
import com.example.luqman.kadesubmission.R
import com.example.luqman.kadesubmission.activity.MatchDetailActivity
import com.example.luqman.kadesubmission.model.Favorite
import com.example.luqman.kadesubmission.ui.MatchRowUI
import com.example.luqman.kadesubmission.util.DateTimeUtil
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.find
import org.jetbrains.anko.startActivity

class FavoriteMatchAdapter(private val favorite: List<Favorite>) :
    RecyclerView.Adapter<FavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            MatchRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return favorite.size
    }

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(favorite[position])
    }

}

class FavoriteViewHolder(view: View, val context: Context) : RecyclerView.ViewHolder(view) {
    private val matchDate: TextView = view.find(R.id.match_date)
    private val matchTime: TextView = view.find(R.id.match_time)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val awayScore: TextView = view.find(R.id.away_score)

    fun bindItem(favorite: Favorite) {
        val sourceDateTimeString = favorite.matchDate + " " + favorite.matchTime
        val sourceDateTimeFormat = "yyyy-MM-dd HH:mm:ssZZ"

        val dateString: String =
            DateTimeUtil.formatDateTime(sourceDateTimeString, sourceDateTimeFormat, "EEE, dd MMM yyyy")
        val timeString: String = DateTimeUtil.formatDateTime(sourceDateTimeString, sourceDateTimeFormat, "HH:mm")

        val dateSpannable = SpannableStringBuilder(dateString)
        dateSpannable.setSpan(StyleSpan(Typeface.BOLD), 0, dateString.length, 0)
        dateSpannable.setSpan(ForegroundColorSpan(Color.GRAY), 0, dateString.length, 0)
        matchDate.text = dateSpannable
        matchTime.text = timeString
        homeTeam.text = favorite.homeTeamName

        val homeScoreText = if (favorite.homeTeamScore == null) "" else favorite.homeTeamScore.toString()
        homeScore.text = homeScoreText
        awayTeam.text = favorite.awayTeamName
        val awayScoreText = if (favorite.awayTeamScore == null) "" else favorite.awayTeamScore.toString()
        awayScore.text = awayScoreText



        itemView.setOnClickListener {
            context.startActivity<MatchDetailActivity>("match_id" to favorite.matchId)
        }
    }
}