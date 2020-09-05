package com.jmyp.boardgamecounter.ui.games

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.jmyp.boardgamecounter.data.Game
import com.jmyp.boardgamecounter.databinding.ItemGameBinding
import com.jmyp.boardgamecounter.utils.convertLongToTime
import com.jmyp.boardgamecounter.utils.loadUrl
import com.jmyp.boardgamecounter.utils.setSafeOnClickListener
import kotlinx.android.synthetic.main.item_game.view.*

class GamesAdapter(private val onItemClickListener: (Game) -> Unit) :
    RecyclerView.Adapter<GamesAdapter.ViewHolder>() {

    private var gameList = ArrayList<Game>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemGameBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(
            binding
        )
    }

    override fun getItemCount(): Int {
        return gameList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val game = gameList[position]
        holder.appBinding.game = game
        holder.appBinding.ivLogo.loadUrl(game.image)
        if(game.date != 0L) {
            holder.itemView.tv_date.text = convertLongToTime(game.date)
        }
        holder.itemView.setSafeOnClickListener { onItemClickListener(game) }
    }

    fun setItems(items: ArrayList<Game>) {
        gameList = items
        notifyDataSetChanged()
    }

    class ViewHolder(val appBinding: ItemGameBinding) :
        RecyclerView.ViewHolder(appBinding.root)
}