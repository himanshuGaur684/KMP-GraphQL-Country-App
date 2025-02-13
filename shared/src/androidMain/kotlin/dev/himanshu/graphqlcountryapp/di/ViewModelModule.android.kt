package dev.himanshu.graphqlcountryapp.di

import dev.himanshu.graphqlcountryapp.viewModel.CountriesViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    viewModel { CountriesViewModel() }
}