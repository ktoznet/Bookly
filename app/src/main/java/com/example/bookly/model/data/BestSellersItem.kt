package com.example.bookly.model.data

data class BestSellersItem(
    val author: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rate: Rate,
    val title: String
)