package com.example.luqman.kadesubmission.main

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
import com.example.luqman.kadesubmission.model.Event
import com.example.luqman.kadesubmission.ui.MatchRowUI
import org.jetbrains.anko.*
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class MatchAdapter(private val events: List<Event>):
        RecyclerView.Adapter<MatchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchViewHolder {
        return MatchViewHolder(
            MatchRowUI().createView(
                AnkoContext.create(parent.context, parent)
            ),
            parent.context
        )
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: MatchViewHolder, position: Int) {
        holder.bindItem(events[position])
    }

}

class MatchViewHolder(view: View, val context: Context): RecyclerView.ViewHolder(view){
    private val matchDate: TextView = view.find(R.id.match_date)
    private val homeTeam: TextView = view.find(R.id.home_team)
    private val homeScore: TextView = view.find(R.id.home_score)
    private val awayTeam: TextView = view.find(R.id.away_team)
    private val awayScore: TextView = view.find(R.id.away_score)

    fun bindItem(event: Event){
        val calendar: Calendar = Calendar.getInstance()
        val date = event.matchDate.toString().split("-")
        calendar.set(date[0].toInt(), date[1].toInt(), date[2].toInt())
        val formatter = SimpleDateFormat("EEE, dd MMM yyyy", Locale.getDefault())
        val dateString: String = formatter.format(calendar.time)

        val dateSpannable = SpannableStringBuilder(dateString)
        dateSpannable.setSpan(StyleSpan(Typeface.BOLD), 0,dateString.length, 0)
        dateSpannable.setSpan(ForegroundColorSpan(Color.GRAY),0, dateString.length, 0)
        matchDate.text =  dateSpannable
        homeTeam.text = event.homeTeam

        val homeScoreText = if(event.homeScore == null) "" else event.homeScore.toString()
        homeScore.text = homeScoreText
        awayTeam.text = event.awayTeam
        val awayScoreText = if(event.awayScore == null) "" else event.awayScore.toString()
        awayScore.text = awayScoreText



        itemView.setOnClickListener {
            context.startActivity<MatchDetail>("match" to event)
        }
    }
}