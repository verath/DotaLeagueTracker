package app.verath.dotaleaguetracker.repository

import app.verath.dotaleaguetracker.api.ResultResponse
import app.verath.dotaleaguetracker.api.StatusResult
import app.verath.dotaleaguetracker.model.ApiErrorResponse
import app.verath.dotaleaguetracker.model.ApiResponse
import app.verath.dotaleaguetracker.model.ApiSuccessResponse
import retrofit2.Call
import retrofit2.Response
import timber.log.Timber

/**
 * Wrapper around retrofit2.Callback<T> that performs application level
 * error checking (HTTP response code, missing data, ...) of the response in
 * addition to the IO/Network layer error checking performed by retrofit. If
 * the returned type of T#getResult implements the StatusResult interface, then
 * StatusResult#isSuccessful is tested as well.
 *
 * The result of the api call is forwarded to the registered callback as an
 * ApiResponse, which can be type switched on to determine if the api call
 * succeeded (ApiSuccessResponse) or failed (ApiErrorResponse).
 */
class ApiResponseCallback<T : ResultResponse<*>>(
        private val callback: ((ApiResponse<T>) -> Unit)?
) : retrofit2.Callback<T> {

    override fun onFailure(call: Call<T>?, t: Throwable?) {
        // We get here for non-application level errors (network, io, parsing of request, ...)
        invokeCallback(ApiErrorResponse(t?.message ?: "Unknown Error"))
    }

    override fun onResponse(call: Call<T>?, response: Response<T>?) {
        val body: T? = response?.body()
        // Check for application-level errors, such as bad HTTP response or missing body
        val apiResponse: ApiResponse<T> =
                if (response == null) {
                    ApiErrorResponse("No response")
                } else if (!response.isSuccessful) {
                    ApiErrorResponse("Bad response code: ${response.code()}")
                } else if (body == null) {
                    ApiErrorResponse("Empty body")
                } else {
                    validateBody(body)
                }

        invokeCallback(apiResponse)
    }

    private fun invokeCallback(apiResponse: ApiResponse<T>) {
        if (apiResponse is ApiErrorResponse) {
            Timber.e("ApiErrorResponse: $apiResponse")
        }
        callback?.invoke(apiResponse)
    }

    private fun validateBody(body: T): ApiResponse<T> {
        val result = body.result
        return if (result == null) {
            ApiErrorResponse("Empty result")
        } else if (result is StatusResult && !result.isSuccessful()) {
            ApiErrorResponse("Bad result status: '${result.error() ?: "Unknown reason"}'")
        } else {
            return ApiSuccessResponse(body)
        }
    }

}
