package com.shanu.stocksapp.presentation.company_listing

import com.shanu.stocksapp.domain.models.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(),
    val isLoading: Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
