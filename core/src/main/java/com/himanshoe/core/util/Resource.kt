package com.himanshoe.core.util

/**
 * Created by Himanshu Singh on 13-04-2021.
 * hello2himanshusingh@gmail.com
 */

sealed class Resource<T>(
    val data: T? = null,
    val error: Throwable? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}