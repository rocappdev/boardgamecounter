package com.jmyp.boardgamecounter.network

import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.utils.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {

    private val service = getApiService()

    fun getGames(): Call<ArrayList<Game>> {
        return service.getGames()
    }

    fun getPoints(gameId: Int): Call<ArrayList<Points>> {
        return service.getPoints(gameId)
    }

    fun setPoints(points: Points): Call<Points> {
        return service.setPoints(points)
    }

    fun getPlayers(): Call<ArrayList<Player>> {
        return service.getPlayers()
    }

    private fun getApiService(): ApiService {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(createLoggingInterceptor())

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient.build())
            .build()
        return retrofit.create(ApiService::class.java)
    }

    private fun createLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }
}