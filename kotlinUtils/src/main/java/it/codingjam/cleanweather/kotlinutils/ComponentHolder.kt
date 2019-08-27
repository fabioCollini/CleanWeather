package it.codingjam.cleanweather.kotlinutils

import java.util.*

interface ComponentHolder {
    operator fun <C : Any> get(componentClass: Class<C>): C?
    fun <C : Any> createComponent(componentClass: Class<C>, component: C)
    fun clearComponents()
}

inline fun <C : Any> ComponentHolder.getOrCreate(componentClass: Class<C>, componentFactory: () -> C): C {
    val value = get(componentClass)
    return if (value == null) {
        val answer = componentFactory()
        createComponent(componentClass, answer)
        answer
    } else {
        value
    }
}

inline fun <reified C : Any> ComponentHolder.getOrCreate(noinline componentFactory: () -> C): C =
        getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> ComponentHolder.getComponent(): C =
        get(C::class.java)
                ?: throw Exception("Component ${C::class.java.simpleName} not available in ${this::class.java.simpleName}")

inline fun <reified C : Any> ComponentHolder.createComponent(noinline componentFactory: () -> C) {
    getOrCreate(C::class.java, componentFactory)
}

class ComponentsMap : ComponentHolder {

    private val moduleComponents = HashMap<Class<*>, Any>()

    override operator fun <C : Any> get(componentClass: Class<C>): C? {
        @Suppress("UNCHECKED_CAST")
        return moduleComponents[componentClass] as C?
    }

    override fun <C : Any> createComponent(componentClass: Class<C>, component: C) {
        moduleComponents[componentClass] = component
    }

    override fun clearComponents() {
        moduleComponents.clear()
    }
}
