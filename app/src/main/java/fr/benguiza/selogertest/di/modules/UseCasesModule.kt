package fr.benguiza.selogertest.di.modules

import dagger.Module
import dagger.Provides
import fr.benguiza.domainlayer.item.*

@Module
class UseCasesModule {

    @Provides
    fun provideRetrieveItemsUseCase(repository: NetworkItemsRepository): RetrieveItemsUseCase {
        return RetrieveItemsUseCase(repository)
    }

    @Provides
    fun provideSaveToDataBaseItemsUseCase(localItemsRepository: LocalItemsRepository): SaveToDataBaseItemsUseCase {
        return SaveToDataBaseItemsUseCase(localItemsRepository)
    }

    @Provides
    fun provideGetItemByIdUseCase(networkItemsRepository: NetworkItemsRepository): GetItemByIdUseCase {
        return GetItemByIdUseCase(networkItemsRepository)
    }

    @Provides
    fun provideGetLocalItemByIdUseCase(localItemsRepository: LocalItemsRepository): GetLocalItemByIdUseCase {
        return GetLocalItemByIdUseCase(localItemsRepository)
    }

    @Provides
    fun provideGetItemsUseCase(localItemsRepository: LocalItemsRepository): GetItemsUseCase {
        return GetItemsUseCase(localItemsRepository)
    }

}