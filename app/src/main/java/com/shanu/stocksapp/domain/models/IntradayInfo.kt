package com.shanu.stocksapp.domain.models

import java.time.LocalDateTime

data class IntradayInfo (
    val date: LocalDateTime,
    val close: Double
)