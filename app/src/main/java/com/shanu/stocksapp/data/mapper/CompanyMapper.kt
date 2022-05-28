package com.shanu.stocksapp.data.mapper

import com.shanu.stocksapp.data.local.CompanyListingEntity
import com.shanu.stocksapp.data.remote.dto.CompanyInfoDto
import com.shanu.stocksapp.domain.models.CompanyInfo
import com.shanu.stocksapp.domain.models.CompanyListing


fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name=name,
        symbol=symbol,
        exchange=exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name=name,
        symbol=symbol,
        exchange=exchange
    )
}

fun CompanyInfoDto.toCompanyInfo() : CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: "",
    )
}