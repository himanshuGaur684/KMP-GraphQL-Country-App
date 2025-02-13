package dev.himanshu.graphqlcountryapp.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.normalizedCache
import dev.himanshu.graphqlcountryapp.GetCountriesQuery

class CountriesRepository {

    private val normalizedCache = MemoryCacheFactory(maxSizeBytes = 10 * 1024 * 1024)

    private val apolloClient = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/")
        .normalizedCache(normalizedCache)
        .build()

    suspend fun getCountries(): Result<GetCountriesQuery.Data?> {
        try {
            val response = apolloClient.query(GetCountriesQuery())
                .execute()
            return Result.success(response.data)
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }


}