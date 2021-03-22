package fr.benguiza.selogertest.di.modules

import dagger.Module
import dagger.Provides
import fr.benguiza.datalayer.items.ItemsApiService
import fr.benguiza.datalayer.items.LocalItemsRepositoryImp
import fr.benguiza.datalayer.items.NetworkItemsRepositoryImp
import fr.benguiza.domainlayer.item.LocalItemsRepository
import fr.benguiza.domainlayer.item.NetworkItemsRepository
import javax.inject.Singleton

@Module
class RepositoriesModule {

    @Provides
    @Singleton
    fun provideItemsRepository(apiService: ItemsApiService): NetworkItemsRepository {
        return NetworkItemsRepositoryImp(apiService)
    }

    @Provides
    @Singleton
    fun provideItemsLocal(): LocalItemsRepository {
        return LocalItemsRepositoryImp()
    }
}