package it.codingjam.cleanweather.utils

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import it.codingjam.cleanweather.kotlinutils.ComponentHolder


inline fun <reified C : Any> Application.getOrCreateAppComponent(noinline componentFactory: () -> C) =
        (this as ComponentHolder).getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> Fragment.getOrCreateAppComponent(noinline componentFactory: () -> C) =
        (requireActivity().application as ComponentHolder).getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> Activity.getOrCreateAppComponent(noinline componentFactory: () -> C) =
        (application as ComponentHolder).getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> Fragment.getOrCreateActivityComponent(noinline componentFactory: () -> C) =
        (requireActivity() as ComponentHolder).getOrCreate(C::class.java, componentFactory)