package com.shanu.stocksapp.data.repository

import com.opencsv.CSVReader
import com.shanu.stocksapp.data.csv.CSVParser
import com.shanu.stocksapp.data.csv.CompanyListingParser
import com.shanu.stocksapp.data.local.StockDatabase
import com.shanu.stocksapp.data.mapper.toCompanyListing
import com.shanu.stocksapp.data.mapper.toCompanyListingEntity
import com.shanu.stocksapp.data.remote.StockApi
import com.shanu.stocksapp.domain.models.CompanyListing
import com.shanu.stocksapp.domain.repository.StockRepository
import com.shanu.stocksapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.io.InputStreamReader
import javax.inject.Singleton
import javax.inject.Inject

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase,
    val companyListingParser: CSVParser<CompanyListing>
): StockRepository {

    private val dao = db.dao
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)
            emit(Resource.Success(
                data = localListings.map {
                    it.toCompanyListing()
                }
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try{
                val response = api.getListings()
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException){
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data."))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error(message = "Couldn't load data."))
                null
            }

            remoteListings?.let { listings ->
                dao.clearCompanyListings()
                dao.insertCompanyListings(
                    listings.map {
                        it.toCompanyListingEntity()
                    }
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map { it.toCompanyListing() }
                ))
                emit(Resource.Loading(false))
            }
        }
    }

}