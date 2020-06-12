package it.codingjam.cleanweather.mainapp

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

class MyTestRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader, appName: String, context: Context): Application =
            super.newApplication(cl, HiltTestApplication::class.java.name, context)
}