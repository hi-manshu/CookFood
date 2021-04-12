package com.himanshoe.core.base.domain

import com.himanshoe.core.util.Resource
import kotlinx.coroutines.flow.Flow

/**
 * Created by Himanshu Singh on 13-04-2021.
 * hello2himanshusingh@gmail.com
 */

/** A Use Case that takes an argument and returns a result. */
interface IBaseUseCase<in I, R : Any> {

    /** Executes this use case with given input. */
    suspend operator fun invoke(input: I): Flow<Resource<R>>
}