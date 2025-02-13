package dev.himanshu.graphqlcountryapp.di

import dev.himanshu.graphqlcountryapp.repository.CountriesRepository
import org.koin.dsl.module

val sharedModule = module {
    single { CountriesRepository() }
}