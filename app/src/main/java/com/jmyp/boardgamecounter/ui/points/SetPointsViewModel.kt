package com.jmyp.boardgamecounter.ui.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.repository.PlayersRepository
import com.jmyp.boardgamecounter.repository.PointsRepository

class SetPointsViewModel : ViewModel() {
    private val pointsRepository = PointsRepository()
    private val playersRepository = PlayersRepository()

    fun setPoints(points: Points) {
        pointsRepository.setPoints(points)
    }

    fun getPlayers(): LiveData<ArrayList<Player>> {
        return playersRepository.getPlayers()
    }
}
