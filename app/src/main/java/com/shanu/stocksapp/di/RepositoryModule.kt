package com.shanu.stocksapp.di

import com.shanu.stocksapp.data.csv.CSVParser
import com.shanu.stocksapp.data.csv.CompanyListingParser
import com.shanu.stocksapp.data.repository.StockRepositoryImpl
import com.shanu.stocksapp.domain.models.CompanyListing
import com.shanu.stocksapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindCompanyListingsParser(
        companyListingParser: CompanyListingParser
    ): CSVParser<CompanyListing>

    @Binds
    @Singleton
    abstract fun bindStockRepository(
        stockRepositoryImpl: StockRepositoryImpl
    ): StockRepository
}