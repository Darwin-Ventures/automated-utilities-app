package com.example.automatedutilitiesapp.di

import com.example.automatedutilitiesapp.di.components.CustomDialog
import com.example.automatedutilitiesapp.di.components.Messaging
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

object AppModule {
    val appModule = module{
        single {
            Messaging(
                androidContext()
            )
        }
        single { CustomDialog() }
    }
}