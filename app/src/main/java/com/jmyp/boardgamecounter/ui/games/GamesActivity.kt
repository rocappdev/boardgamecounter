package com.jmyp.boardgamecounter.ui.games

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jmyp.boardgamecounter.R
import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.ui.points.PointsActivity
import kotlinx.android.synthetic.main.activity_games.*
import org.jetbrains.anko.intentFor

class GamesActivity : AppCompatActivity() {

    private val viewModel: GamesViewModel by viewModels()
    private lateinit var adapter: GamesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_games)
        instances()
        observers()
    }

    private fun instances() {
        adapter = GamesAdapter { game ->
            onGameClick(game)
        }
        rv_games.adapter = adapter
    }

    private fun observers() {
        viewModel.getGames().observe(this, { games ->
            adapter.setItems(games)
        })
    }

    private fun onGameClick(game: Game) {
        startActivity(intentFor<PointsActivity>(GAME_ID to game.id))
    }

    companion object {
        const val GAME_ID = "game"
    }
}