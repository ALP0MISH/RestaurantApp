package com.example.restaurantapp.di

import com.example.socialapp.data.cloud.source.ChangeUserInfoDataSours
import com.example.socialapp.data.cloud.source.ChangeUserInfoDataSoursImpl
import com.example.socialapp.data.cloud.source.FetchFromBasketCloudDataSource
import com.example.socialapp.data.cloud.source.FetchFromBasketCloudDataSourceImpl
import com.example.socialapp.data.cloud.source.MenuCloudDataSource
import com.example.socialapp.data.cloud.source.MenuCloudDataSourceImpl
import com.google.android.gms.common.api.internal.BaseImplementation
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface CloudDataSourceModel {

    @Binds
    fun bindMenuCloudDataSource(
        implementation: MenuCloudDataSourceImpl
    ): MenuCloudDataSource

    @Binds
    fun bindFetchFromBasketCloudDataSource(
        implementation: FetchFromBasketCloudDataSourceImpl
    ): FetchFromBasketCloudDataSource

    @Binds
    fun bindChangeUserInfoDataSours(
        implementation: ChangeUserInfoDataSoursImpl
    ): ChangeUserInfoDataSours
}