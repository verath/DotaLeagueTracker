package app.verath.dotaleaguetracker.model

import com.google.gson.annotations.SerializedName

data class DotaLeague(
        val name: String,
        @SerializedName("leagueid")
        val leagueId: Int,
        val description: String?,
        val tournamentUrl: String?
)
