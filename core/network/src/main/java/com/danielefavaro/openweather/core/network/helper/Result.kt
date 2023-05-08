/*
 * Copyright (C) 2022 Shortcut European Apps AB - All Rights Reserved
 * This file is part of ShortcutTemplate
 * Created on 2022-02-25, 15:23
 */

package com.danielefavaro.openweather.core.network.helper

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()
    data class Failure(val code: Int, val message: String? = null) : Result<Nothing>()
}
