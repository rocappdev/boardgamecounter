package com.jmyp.boardgamecounter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GamesRepository {

    private val apiClient = ApiClient()

    fun getGames() : LiveData<ArrayList<Game>> {
        val responseData = MutableLiveData<ArrayList<Game>>()

        apiClient.getGames().enqueue(object : Callback<ArrayList<Game>> {

            override fun onResponse(
                call: Call<ArrayList<Game>>,
                response: Response<ArrayList<Game>>
            ) {
                if (response.isSuccessful) {
                    responseData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Game>>, t: Throwable) {

            }
        })

        return responseData
    }
}