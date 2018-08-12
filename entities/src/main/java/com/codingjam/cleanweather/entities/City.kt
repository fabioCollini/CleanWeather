package com.codingjam.cleanweather.entities

data class City(
        val id: Int,
        val name: String,
        val country: String
) {
    override fun toString() = "$name ($country)"
}