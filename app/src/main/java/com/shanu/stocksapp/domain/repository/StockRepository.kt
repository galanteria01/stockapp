package com.shanu.stocksapp.domain.repository

import com.shanu.stocksapp.domain.models.CompanyListing
import com.shanu.stocksapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>
}