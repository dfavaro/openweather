package com.danielefavaro.openweather.core.navigation

internal fun toRoute(vararg s: String) = s.joinToString("/")
internal fun toPathWithParameter(vararg s: String) = s.joinToString("/") { "{$it}" }
internal fun toPathWithOptionalParameter(vararg s: String) = s.joinToString("?") { "$it={$it}" }

abstract class BaseDestination {
    abstract val route: String
    open val params: String = ""
    val routeParams: String
        get() = "$route/$params"
    val routeOptionalParams: String
        get() = "$route?$params"
}
