package com.shanu.stocksapp.domain.models

data class CompanyInfo (
    val symbol: String,
    val description: String,
    val name: String,
    val country: String,
    val industry: String
)