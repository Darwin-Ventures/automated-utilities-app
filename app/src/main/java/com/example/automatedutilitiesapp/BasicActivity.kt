package com.example.automatedutilitiesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.automatedutilitiesapp.R
import com.example.automatedutilitiesapp.di.AppModule.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BasicActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init DI
        startKoin {
            androidLogger()
            androidContext(this@BasicActivity)
            modules(appModule)
        }
    }
}
