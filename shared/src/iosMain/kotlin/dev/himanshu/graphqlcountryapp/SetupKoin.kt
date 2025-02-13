package dev.himanshu.graphqlcountryapp

import dev.himanshu.graphqlcountryapp.di.sharedModule
import dev.himanshu.graphqlcountryapp.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(
            sharedModule,
            viewModelModule
        )
    }
}