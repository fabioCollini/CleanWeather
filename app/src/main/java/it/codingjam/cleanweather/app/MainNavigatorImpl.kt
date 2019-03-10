package it.codingjam.cleanweather.app

import android.app.Activity
import android.content.ComponentName
import android.content.Intent
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Inject

@AppSingleton
class MainNavigatorImpl @Inject constructor(): MainNavigator {
    override fun openDetail(activity: Activity) {
        activity.startActivity(Intent().setComponent(ComponentName(activity.packageName, "it.codingjam.cleanweather.detail.DetailActivity")))
    }
}