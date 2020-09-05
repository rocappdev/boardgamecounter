package com.jmyp.boardgamecounter.ui.games

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.repository.GamesRepository

class GamesViewModel : ViewModel() {

    private val repository = GamesRepository()

    fun getGames() : LiveData<ArrayList<Game>> {
        return repository.getGames()
    }
}