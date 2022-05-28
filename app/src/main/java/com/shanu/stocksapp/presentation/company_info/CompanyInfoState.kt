package com.shanu.stocksapp.presentation.company_info

import com.shanu.stocksapp.domain.models.CompanyInfo
import com.shanu.stocksapp.domain.models.IntradayInfo

data class CompanyInfoState (
    val stockInfo: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo ?= null,
    val isLoading: Boolean = false,
    val error: String? = null
)