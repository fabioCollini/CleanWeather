package com.codingjam.cleanweather.entities

data class City(
        val name: String,
        val country: String
) {
    override fun toString() = "$name ($country)"
}