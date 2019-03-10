package it.codingjam.cleanweather.utils

import android.app.Activity
import androidx.fragment.app.Fragment
import java.util.*

interface ComponentHolder {
    fun <C : Any> getOrCreate(componentClass: Class<C>, componentFactory: () -> C): C
}

inline fun <reified C : Any> ComponentHolder.getOrCreate(noinline componentFactory: () -> C): C =
        getOrCreate(C::class.java, componentFactory)

class ComponentsMap : ComponentHolder {
    private val moduleComponents = HashMap<Class<*>, Any>()

    override fun <C : Any> getOrCreate(componentClass: Class<C>, componentFactory: Function0<C>): C =
            moduleComponents.getOrPut(componentClass, componentFactory) as C
}

inline fun <reified C : Any> Fragment.getOrCreateAppComponent(noinline componentFactory: () -> C) =
        (requireActivity().application as ComponentHolder).getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> Activity.getOrCreateAppComponent(noinline componentFactory: () -> C) =
        (application as ComponentHolder).getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> Fragment.getOrCreateActivityComponent(noinline componentFactory: () -> C) =
        (requireActivity() as ComponentHolder).getOrCreate(C::class.java, componentFactory)