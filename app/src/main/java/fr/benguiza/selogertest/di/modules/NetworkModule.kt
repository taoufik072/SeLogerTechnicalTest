package fr.benguiza.selogertest.di.modules

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import fr.benguiza.commons_android.TSchedulers
import fr.benguiza.datalayer.items.ItemsApiService
import fr.benguiza.selogertest.BuildConfig
import fr.benguiza.selogertest.network.HeaderInterceptor
import fr.benguiza.selogertest.network.NetworkConfig
import fr.benguiza.selogertest.network.buildHttpClient
import fr.benguiza.selogertest.network.networkConfig
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideNetworkConfig(): NetworkConfig {
        return networkConfig(BuildConfig.FLAVOR)
    }

    @Provides
    @Singleton
    fun provideHeaderInterceptor(): HeaderInterceptor {
        return HeaderInterceptor()
    }

    @Provides
    @Singleton
    fun provideHttpCache(context: Context): Cache {
        return Cache(context.cacheDir, NetworkConfig.CACHE_SIZE)
    }

    @Provides
    @Singleton
    fun provideHttpClient(
        cache: Cache,
        networkConfig: NetworkConfig,
        headerInterceptor: HeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder().buildHttpClient(
            cache,
            networkConfig,
            headerInterceptor = headerInterceptor
        )
    }

    @Provides
    @Singleton
    fun provideRetrofit(
        networkConfig: NetworkConfig,
        gson: Gson,
        httpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .client(httpClient)
            .baseUrl(networkConfig.baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(TSchedulers.io))
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }


    @Provides
    @Singleton
    fun provideItemsService(retrofit: Retrofit): ItemsApiService {
        return retrofit.create(ItemsApiService::class.java)
    }

}
