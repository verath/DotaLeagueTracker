package app.verath.dotaleaguetracker

data class DotaLeague(
        val name: String,
        val leagueId: Int,
        val description: String?,
        val tournamentUrl: String?
)
