package it.codingjam.cleanweather.kotlinutils

import java.util.*

interface ComponentHolder {
    fun <C : Any> getOrCreate(componentClass: Class<C>, componentFactory: () -> C): C
}

inline fun <reified C : Any> ComponentHolder.getOrCreate(noinline componentFactory: () -> C): C =
        getOrCreate(C::class.java, componentFactory)

inline fun <reified C : Any> ComponentHolder.get(): C =
        getOrCreate(C::class.java) {
            throw Exception("Component ${C::class.java.simpleName} not available in ${this::class.java.simpleName}")
        }

class ComponentsMap : ComponentHolder {
    val moduleComponents = HashMap<Class<*>, Any>()

    @Suppress("UNCHECKED_CAST")
    override fun <C : Any> getOrCreate(componentClass: Class<C>, componentFactory: () -> C): C =
            moduleComponents.getOrPut(componentClass, componentFactory) as C

    inline fun <reified C : Any> createComponent(noinline componentFactory: () -> C) {
        getOrCreate(C::class.java, componentFactory)
    }
}

inline fun <reified T> loadSingleService(): T {
    val provider = ServiceLoader.load(T::class.java, T::class.java.classLoader)
    return provider.iterator().next()
}

interface DependenciesCreator<T> {
    fun dependencies(componentHolder: ComponentHolder): T
}
