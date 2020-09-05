package com.jmyp.boardgamecounter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayersRepository {

    private val apiClient = ApiClient()

    fun getPlayers(): LiveData<ArrayList<Player>> {
        val responseData = MutableLiveData<ArrayList<Player>>()

        apiClient.getPlayers().enqueue(object : Callback<ArrayList<Player>> {

            override fun onResponse(
                call: Call<ArrayList<Player>>,
                response: Response<ArrayList<Player>>
            ) {
                if (response.isSuccessful) {
                    responseData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Player>>, t: Throwable) {

            }
        })

        return responseData
    }
}