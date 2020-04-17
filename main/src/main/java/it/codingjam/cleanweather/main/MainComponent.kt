package it.codingjam.cleanweather.main

import android.app.Activity

interface MainComponent {
    fun inject(activity: MainActivity)
}

interface MainNavigator {
    fun openDetail(activity: Activity)
}