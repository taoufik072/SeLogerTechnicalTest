package fr.benguiza.selogertest.di.modules

import fr.benguiza.selogertest.app.SeLogerApplication
import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import fr.benguiza.selogertest.tools.extensions.AndroidResources
import fr.benguiza.selogertest.tools.extensions.Resources
import javax.inject.Singleton


@Module
class TestAppModule(private val tapplication: SeLogerApplication) {

    @Provides
    @Singleton
    fun provideContext(): Context {
        return tapplication
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun provideResources(context: Context): Resources {
        return AndroidResources(
            context.resources
        )
    }

}