package it.codingjam.cleanweather.app

import android.app.Activity
import android.content.Intent
import it.codingjam.cleanweather.detail.DetailActivity
import it.codingjam.cleanweather.main.MainNavigator

class MainNavigatorImpl : MainNavigator {
    override fun openDetail(activity: Activity) {
        activity.startActivity(Intent(activity, DetailActivity::class.java))
    }
}