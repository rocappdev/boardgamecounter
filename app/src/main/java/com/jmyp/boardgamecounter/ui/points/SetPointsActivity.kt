package com.jmyp.boardgamecounter.ui.points

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.jmyp.boardgamecounter.R
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.ui.games.GamesActivity
import com.jmyp.boardgamecounter.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.activity_points.*
import kotlinx.android.synthetic.main.item_player_pts.view.*
import kotlinx.android.synthetic.main.item_points.*
import org.jetbrains.anko.forEachChild
import java.util.*
import kotlin.collections.ArrayList

class SetPointsActivity : AppCompatActivity() {

    private val viewModel: SetPointsViewModel by viewModels()
    private var gameId: Int? = null
    private var players = ArrayList<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_set_points)
        instances()
        observers()
        actions()
    }

    private fun instances() {
        gameId = intent?.extras?.get(GamesActivity.GAME_ID) as Int?
    }

    private fun observers() {
        viewModel.getPlayers().observe(this) { players->
            this.players = players
            players.forEach { player ->
                val playerView =
                    layoutInflater.inflate(R.layout.item_player_pts, ll_players, false)
                playerView.id = player.id
                playerView.tv_name.text = player.name
                ll_players.addView(playerView)
            }
        }
    }

    private fun actions() {
        fab_add_points.setSafeOnClickListener {
            val players = ArrayList<Player>()
            ll_players.forEachChild {
                if(it.et_points.text.isNotEmpty()) {
                    players.add(Player(it.id, it.tv_name.text.toString(), it.et_points.text.toString()))
                }
            }
            if (players.isNotEmpty()) {
                val points = Points(null, gameId!!, players, Date().time)
                viewModel.setPoints(points)
            }
        }
    }
}