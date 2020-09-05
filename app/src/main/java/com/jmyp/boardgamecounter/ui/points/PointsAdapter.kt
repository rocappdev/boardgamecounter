package com.jmyp.boardgamecounter.ui.points

import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jmyp.boardgamecounter.R
import com.jmyp.boardgamecounter.data.Player
import com.jmyp.boardgamecounter.data.Points
import com.jmyp.boardgamecounter.databinding.ItemPointsBinding
import com.jmyp.boardgamecounter.utils.convertLongToTime
import kotlinx.android.synthetic.main.item_player.view.*
import kotlinx.android.synthetic.main.item_points.view.*

class PointsAdapter :
    RecyclerView.Adapter<PointsAdapter.ViewHolder>() {

    private lateinit var layoutInflater: LayoutInflater
    private var pointsList = ArrayList<Points>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemPointsBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return pointsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val points = pointsList[position]
        holder.appBinding.points = points
        holder.itemView.tv_date.text = convertLongToTime(points.date)
        holder.itemView.ll_players.removeAllViews()

        var bestPlayer = getBestPlayer(points.players)

        points.players.forEach { player ->
            val playerView =
                layoutInflater.inflate(R.layout.item_player, holder.itemView.ll_players, false)
            playerView.tv_name.text = player.name
            playerView.tv_points.text = "${player.points}pts"
            if (player.id == bestPlayer) {
                playerView.tv_name.setTypeface(null, Typeface.BOLD)
                playerView.tv_points.setTypeface(null, Typeface.BOLD)
            }
            holder.itemView.ll_players.addView(playerView)
        }
    }

    fun setItems(items: ArrayList<Points>) {
        pointsList = items
        notifyDataSetChanged()
    }

    private fun getBestPlayer(players: ArrayList<Player>): Int {
        var bestPlayer = 0
        var bestPoints = 0
        players.forEach { player ->
            if (player.points.toInt() > bestPoints) {
                bestPoints = player.points.toInt()
                bestPlayer = player.id
            }
        }
        return bestPlayer
    }

    class ViewHolder(val appBinding: ItemPointsBinding) :
        RecyclerView.ViewHolder(appBinding.root)
}