package com.jmyp.boardgamecounter.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.network.ApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PointsRepository {

    private val apiClient = ApiClient()

    fun getPoints(gameId: Int): LiveData<ArrayList<Points>> {
        val responseData = MutableLiveData<ArrayList<Points>>()

        apiClient.getPoints(gameId).enqueue(object : Callback<ArrayList<Points>> {

            override fun onResponse(
                call: Call<ArrayList<Points>>,
                response: Response<ArrayList<Points>>
            ) {
                if (response.isSuccessful) {
                    responseData.value = response.body()
                }
            }

            override fun onFailure(call: Call<ArrayList<Points>>, t: Throwable) {

            }
        })

        return responseData
    }

    fun setPoints(points: Points): LiveData<Points> {
        val responseData = MutableLiveData<Points>()

        apiClient.setPoints(points).enqueue(object : Callback<Points> {

            override fun onResponse(
                call: Call<Points>,
                response: Response<Points>
            ) {
                if (response.isSuccessful) {
                    responseData.value = response.body()
                }
            }

            override fun onFailure(call: Call<Points>, t: Throwable) {

            }
        })

        return responseData
    }
}