package com.jmyp.boardgamecounter.ui.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.repository.PointsRepository

class PointsViewModel : ViewModel() {
    private val repository = PointsRepository()

    fun getPoints(gameId: Int): LiveData<ArrayList<Points>> {
        return repository.getPoints(gameId)
    }
}
