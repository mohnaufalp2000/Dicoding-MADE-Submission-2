package com.naufal.techmedia

import android.app.Application
import com.naufal.techmedia.core.di.databaseModule
import com.naufal.techmedia.core.di.networkModule
import com.naufal.techmedia.core.di.repositoryModule
import com.naufal.techmedia.di.useCaseModule
import com.naufal.techmedia.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.NONE)
            androidContext(this@MyApplication)
            modules(
                listOf(
                    databaseModule,
                    networkModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }
}