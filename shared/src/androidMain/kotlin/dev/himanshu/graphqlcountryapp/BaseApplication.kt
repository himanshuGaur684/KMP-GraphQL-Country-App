package dev.himanshu.graphqlcountryapp

import android.app.Application
import dev.himanshu.graphqlcountryapp.di.sharedModule
import dev.himanshu.graphqlcountryapp.di.viewModelModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(
                sharedModule,
                viewModelModule
            )
        }
    }
}