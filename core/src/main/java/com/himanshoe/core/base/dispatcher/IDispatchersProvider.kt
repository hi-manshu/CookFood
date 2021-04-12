package com.himanshoe.core.base.dispatcher

/**
 * Created by Himanshu Singh on 12-04-2021.
 * hello2himanshusingh@gmail.com
 */
class IDispatchersProvider : DispatchersProvider {
    override val ui: CoroutineDispatcher
        get
    override val io: CoroutineDispatcher
        get() = TODO("Not yet implemented")
    override val default: CoroutineDispatcher
        get() = TODO("Not yet implemented")
}