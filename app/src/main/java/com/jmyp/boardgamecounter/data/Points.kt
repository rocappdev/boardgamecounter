package com.jmyp.boardgamecounter.data

data class Points(
    val id: Int?,
    val gamesId: Int,
    val players: ArrayList<Player>,
    val date: Long
)