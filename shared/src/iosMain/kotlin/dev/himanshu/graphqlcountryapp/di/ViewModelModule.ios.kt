package dev.himanshu.graphqlcountryapp.di

import dev.himanshu.graphqlcountryapp.viewModel.CountriesViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.get
import org.koin.core.component.inject
import org.koin.core.module.Module
import org.koin.dsl.module

actual val viewModelModule: Module = module {
    single { CountriesViewModel() }
}

object SharedViewModelModule : KoinComponent{
    fun getCountriesViewModel( ) : CountriesViewModel = get<CountriesViewModel>()
}