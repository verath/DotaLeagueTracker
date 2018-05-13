package app.verath.dotaleaguetracker.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import timber.log.Timber


interface Dota2Service {

    @GET("/IDOTA2Match_570/GetLeagueListing/v1/?language=en")
    fun listLeagues(): Call<ListLeaguesResponse>

    companion object {
        private const val BASE_URL = "http://api.steampowered.com/"

        fun create(apiKey: String): Dota2Service {
            val logger = HttpLoggingInterceptor({ msg ->
                Timber.d(msg)
            }).setLevel(HttpLoggingInterceptor.Level.BASIC)

            val apiKeyAdder = Interceptor({ chain ->
                // Add key query param to all requests
                val req = chain.request()
                val newUrl = req.url().newBuilder().addQueryParameter("key", apiKey).build()
                val newReq = req.newBuilder().url(newUrl).build()
                chain.proceed(newReq)
            })
            val client = OkHttpClient.Builder()
                    .addInterceptor(logger)
                    .addInterceptor(apiKeyAdder)
                    .build()
            val gson = GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
            return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
                    .create(Dota2Service::class.java)
        }
    }
}
