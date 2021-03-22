package fr.benguiza.selogertest.app

import android.app.Application
import android.content.ComponentCallbacks2
import com.bumptech.glide.Glide
import com.jakewharton.threetenabp.AndroidThreeTen
import fr.benguiza.commons.tools.Logger
import fr.benguiza.commons_android.TSchedulers
import fr.benguiza.selogertest.BuildConfig
import fr.benguiza.selogertest.di.AppComponent
import fr.benguiza.selogertest.di.DaggerTAppComponent
import fr.benguiza.selogertest.di.TestAppComponent
import fr.benguiza.selogertest.di.modules.AppModule
import fr.benguiza.selogertest.di.modules.TestAppModule
import fr.benguiza.selogertest.tools.LoggerDelegate
import io.realm.Realm
import io.realm.RealmConfiguration
import timber.log.Timber
import java.util.*


class SeLogerApplication : Application(), AppComponent {

    private lateinit var appComponent: AppComponent
    private var isRunningTest: Boolean? = null

    override fun onCreate() {
        super.onCreate()
        initLogger()
        initRealm()
        initThreeTen()
        initSchedulers()
        initDaggerGraph()
    }

    override fun onTrimMemory(level: Int) {
        super.onTrimMemory(level)
        if (level != ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
            with(Glide.get(this)) {
                trimMemory(level)
                clearMemory()
            }
        }
    }

    private fun initRealm() {

        // Initialize Realm//
        Realm.init(this)
        val realmConfiguration = RealmConfiguration.Builder()
            .name("myapp.realm")
            .schemaVersion(1)

        Realm.getDefaultModule()?.let { realmConfiguration.addModule(it) }
        val realmConfBuilder = realmConfiguration.build()
        Realm.setDefaultConfiguration(realmConfBuilder)


    }


    /* ------------------------------------- */
    /*             AppComponent              */
    /* ------------------------------------- */

    override fun fragmentsComponent() =  appComponent.fragmentsComponent()
    override fun activitiesComponent() = appComponent.activitiesComponent()


     fun initDaggerGraph() {
        appComponent =
            DaggerTAppComponent.builder().appModule(AppModule(this)).build()

    }

    private fun initLogger() {
        if (BuildConfig.LOG_ENABLE) {
            Timber.plant(Timber.DebugTree())
        }
        Logger.use(LoggerDelegate())
    }

    private fun initSchedulers() = TSchedulers.init()

    private fun initThreeTen() = AndroidThreeTen.init(this)





}