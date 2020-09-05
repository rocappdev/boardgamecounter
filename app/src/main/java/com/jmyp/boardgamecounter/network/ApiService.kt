package com.jmyp.boardgamecounter.network

import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("games?_sort=date&_order=desc")
    fun getGames(
    ): Call<ArrayList<Game>>

    @GET("points?_sort=date&_order=desc")
    fun getPoints(
        @Query("gamesId") gameId: Int
    ): Call<ArrayList<Points>>

    @POST("points")
    @Headers("Content-Type: application/json")
    fun setPoints(
        @Body points: Points
    ): Call<Points>

    @GET("players")
    fun getPlayers(
    ): Call<ArrayList<Player>>

}
