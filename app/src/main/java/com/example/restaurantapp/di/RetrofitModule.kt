package com.example.restaurantapp.di

import android.content.Context
import android.net.ConnectivityManager
import com.example.socialapp.data.cloud.service.BasketService
import com.example.socialapp.data.cloud.service.LoginService
import com.example.socialapp.data.cloud.service.MenuService
import com.example.socialapp.data.cloud.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://parseapi.back4app.com/classes/"
private const val APPLICATION_ID = "gSBA9pSD4VXRuatovUeQxbXN6HVoMHsqmmrG1EFd"
private const val REST_API_KYE = "NTHF4B8vG2JQKTwGVpFJeHH1LJzWa3VsTK3VeyiY"

@Module
@InstallIn(SingletonComponent::class)
class RetrofitModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .addInterceptor(
                        Interceptor { chain ->
                            val request = chain.request()
                                .newBuilder()
                                .addHeader(
                                    name = "X-Parse-Application-Id",
                                    value = APPLICATION_ID
                                )
                                .addHeader(
                                    name = "X-Parse-REST-API-Key",
                                    value = REST_API_KYE
                                )
                                .addHeader(
                                    name = "Content-Type",
                                    value = "application/json"
                                )
                                .build()
                            return@Interceptor chain.proceed(request = request)
                        },
                    ).build()
            ).build()
    }

    @Provides
    fun provideLoginService(
        retrofit: Retrofit
    ): LoginService = retrofit.create(LoginService::class.java)

    @Provides
    fun provideUserService(
        retrofit: Retrofit
    ): UserService = retrofit.create(UserService::class.java)

    @Provides
    fun provideMenuService(
        retrofit: Retrofit
    ): MenuService = retrofit.create(MenuService::class.java)

    @Provides
    fun provideBasketService(
        retrofit: Retrofit
    ): BasketService = retrofit.create(BasketService::class.java)

    @Provides
    fun provideConnectivityManager(@ApplicationContext context: Context): ConnectivityManager {
        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    }
}