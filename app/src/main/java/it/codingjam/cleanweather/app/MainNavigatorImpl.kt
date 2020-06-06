package it.codingjam.cleanweather.app

import android.app.Activity
import android.content.Intent
import dagger.hilt.android.scopes.ActivityScoped
import it.codingjam.cleanweather.detail.DetailActivity
import it.codingjam.cleanweather.main.MainNavigator
import javax.inject.Inject

@ActivityScoped
class MainNavigatorImpl @Inject constructor(
        private val activity: Activity
) : MainNavigator {
    override fun openDetail() {
        activity.startActivity(Intent(activity, DetailActivity::class.java))
    }
}