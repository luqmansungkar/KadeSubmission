package com.example.luqman.kadesubmission.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import java.util.*

data class Event(
    @SerializedName("idEvent")
    var eventId: String? = null,

    @SerializedName("strHomeTeam")
    var homeTeam: String? = null,

    @SerializedName("strAwayTeam")
    var awayTeam: String? = null,

    @SerializedName("intHomeScore")
    var homeScore: Int? = null,

    @SerializedName("intAwayScore")
    var awayScore: Int? = null,

    @SerializedName("idHomeTeam")
    var homeTeamId: String? = null,

    @SerializedName("idAwayTeam")
    var awayTeamId: String? = null,

    @SerializedName("dateEvent")
    var matchDate: String? = null,

    @SerializedName("strHomeGoalDetails")
    var homeGoalDetails: String? = null,

    @SerializedName("strAwayGoalDetails")
    var awayGoalDetails: String? = null,

    @SerializedName("intHomeShots")
    var homeShots: Int? = null,

    @SerializedName("intAwayShots")
    var awayShots: Int? = null,

    @SerializedName("strHomeLineupGoalkeeper")
    var homeGk: String? = null,

    @SerializedName("strHomeLineupDefense")
    var homeDefense: String? = null,

    @SerializedName("strHomeLineupMidfield")
    var homeMid: String? = null,

    @SerializedName("strHomeLineupForward")
    var homeForward: String? = null,

    @SerializedName("strAwayLineupGoalkeeper")
    var awayGk: String? = null,

    @SerializedName("strAwayLineupDefense")
    var awayDefense: String? = null,

    @SerializedName("strAwayLineupMidfield")
    var awayMid: String? = null,

    @SerializedName("strAwayLineupForward")
    var awayForward: String? = null

): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(eventId)
        parcel.writeString(homeTeam)
        parcel.writeString(awayTeam)
        parcel.writeValue(homeScore)
        parcel.writeValue(awayScore)
        parcel.writeString(homeTeamId)
        parcel.writeString(awayTeamId)
        parcel.writeString(matchDate)
        parcel.writeString(homeGoalDetails)
        parcel.writeString(awayGoalDetails)
        parcel.writeValue(homeShots)
        parcel.writeValue(awayShots)
        parcel.writeString(homeGk)
        parcel.writeString(homeDefense)
        parcel.writeString(homeMid)
        parcel.writeString(homeForward)
        parcel.writeString(awayGk)
        parcel.writeString(awayDefense)
        parcel.writeString(awayMid)
        parcel.writeString(awayForward)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Event> {
        override fun createFromParcel(parcel: Parcel): Event {
            return Event(parcel)
        }

        override fun newArray(size: Int): Array<Event?> {
            return arrayOfNulls(size)
        }
    }
}