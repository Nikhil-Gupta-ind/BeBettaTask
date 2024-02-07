package com.example.bebettatask.data.model

data class HomeFeed(
    val `data`: List<Data>
) {
    data class Data(
        val type: String,
        val title: String,
        val scores: List<Score>,
        val tickr: List<Tickr>,
        val image: String,
        val count: String,
        val questions: List<Question>,
        val videos: List<Video>
    ) {
        data class Score(
            val id: Int,
            val flag1: String,
            val flag2: String,
            val name1: String,
            val name2: String,
            val score1: Int,
            val score2: Int,
            val time: String
        )

        data class Tickr(
            val id: Int,
            val image: String,
            val text: String,
            val hasBlueTick: Boolean
        )

        data class Question(
            val id: Int,
            val question: String,
            val coins: String
        )

        data class Video(
            val id: Int,
            val thumbnail: String,
            val duration: String
        )
    }
}