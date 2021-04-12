package com.himanshoe.core.base.di

import com.himanshoe.core.base.dispatcher.DispatchersProvider
import com.himanshoe.core.base.dispatcher.IDispatchersProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Created by Himanshu Singh on 13-04-2021.
 * hello2himanshusingh@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
class DispatcherModule {

    @Provides
    fun provideDispatcher(iDispatchersProvider: IDispatchersProvider): DispatchersProvider {
        return iDispatchersProvider
    }

}