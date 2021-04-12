package com.himanshoe.core.util

import kotlinx.coroutines.flow.Flow

/**
 * Created by Himanshu Singh on 13-04-2021.
 * hello2himanshusingh@gmail.com
 */

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
): Flow<Resource<ResultType>> {
    return flow {
        val data: ResultType = query().first()

        val flow = if (shouldFetch(data)) {
            emit(Resource.Loading(data))

            try {
                saveFetchResult(fetch())
                query().map { Resource.Success(it) }
            } catch (throwable: Throwable) {
                query().map { Resource.Error(throwable, it) }
            }
        } else {
            query().map { Resource.Success(it) }
        }

        emitAll(flow)
    }
}