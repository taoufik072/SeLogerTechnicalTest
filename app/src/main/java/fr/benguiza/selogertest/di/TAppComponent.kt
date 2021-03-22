package fr.benguiza.selogertest.di

import dagger.Component
import fr.benguiza.selogertest.di.modules.*
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, NetworkModule::class, RepositoriesModule::class,
        UseCasesModule::class, ViewModelsModule::class]
)
interface TAppComponent : AppComponent

