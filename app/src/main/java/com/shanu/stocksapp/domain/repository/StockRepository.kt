package com.shanu.stocksapp.domain.repository

import com.shanu.stocksapp.domain.models.CompanyInfo
import com.shanu.stocksapp.domain.models.CompanyListing
import com.shanu.stocksapp.domain.models.IntradayInfo
import com.shanu.stocksapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>>

    suspend fun getIntradayInfo(
        symbol: String
    ): Resource<List<IntradayInfo>>

    suspend fun getCompanyInfo(
        symbol: String
    ): Resource<CompanyInfo>
}