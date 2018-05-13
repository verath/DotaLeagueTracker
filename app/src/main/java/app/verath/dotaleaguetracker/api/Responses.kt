package app.verath.dotaleaguetracker.api

import app.verath.dotaleaguetracker.model.DotaLeague

/**
 * A ResultResponse models a response that wraps the actual result inside a
 * result object.
 * @param T Type of the wrapped result object
 */
interface ResultResponse<out T> {
    val result: T?
}

/**
 * A StatusResult is a result object that knows if it represents a successful result
 * or not. Many (but not all) of the steam web api methods returns a status int as
 * part of the result object, which can be used for this check.
 */
interface StatusResult {
    /**
     * isSuccessful returns true iff the result object indicates it is a successful
     * result.
     */
    fun isSuccessful(): Boolean

    /**
     * if #isSuccessful return false, then error returns a string string reason for
     * why the result was not considered successful, if the reason is known.
     */
    fun error(): String?
}

data class ListLeaguesResponse(override val result: ListLeaguesResult) : ResultResponse<ListLeaguesResult>
data class ListLeaguesResult(val leagues: List<DotaLeague>)
