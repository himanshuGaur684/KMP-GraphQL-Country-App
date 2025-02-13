package dev.himanshu.graphqlcountryapp.repository

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.cache.normalized.ApolloStore
import com.apollographql.apollo.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo.cache.normalized.normalizedCache
import dev.himanshu.graphqlcountryapp.GetCountriesQuery

class CountriesRepository {

    private val cache = MemoryCacheFactory(10*1024*1024)

    private val apolloClient  = ApolloClient.Builder()
        .serverUrl("https://countries.trevorblades.com/")
        .normalizedCache(cache)
        .build()

    suspend fun getCountries():Result<GetCountriesQuery.Data?>{
        return try {
            val result = apolloClient.query(GetCountriesQuery())
                .execute()
            Result.success(result.data)
        }catch (e:Exception){
            Result.failure(e)
        }
    }



}