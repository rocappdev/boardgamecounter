package com.jmyp.boardgamecounter.ui.points

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.jmyp.boardgamecounter.R
import com.jmyp.boardgamecounter.ui.games.GamesActivity
import com.jmyp.boardgamecounter.utils.hide
import com.jmyp.boardgamecounter.utils.setSafeOnClickListener
import com.jmyp.boardgamecounter.utils.show
import kotlinx.android.synthetic.main.activity_points.*
import org.jetbrains.anko.intentFor


class PointsActivity : AppCompatActivity() {

    private val viewModel: PointsViewModel by viewModels()
    private lateinit var adapter: PointsAdapter
    private var gameId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_points)
        instances()
        observers()
        actions()
    }

    private fun instances() {
        adapter = PointsAdapter()
        rv_points.adapter = adapter
        gameId = intent?.extras?.get(GamesActivity.GAME_ID) as Int?
    }

    private fun observers() {
        viewModel.getPoints(gameId!!).observe(this) { points ->
            adapter.setItems(points)
        }
    }

    private fun actions() {
        fab_add_points.setSafeOnClickListener {
            startActivity(intentFor<SetPointsActivity>(GAME_ID to gameId))
        }
//        tv_show_points_list.setSafeOnClickListener {
//            showPointsList()
//        }
    }

    private fun showPointsList() {
        if(rv_points.isVisible) {
            tv_show_points_list.text = "Mostrar puntos"
            rv_points.hide()
        } else {
            tv_show_points_list.text = "Ocultar puntos"
            rv_points.show()
        }
    }

    companion object {
        const val GAME_ID = "game"
    }

}