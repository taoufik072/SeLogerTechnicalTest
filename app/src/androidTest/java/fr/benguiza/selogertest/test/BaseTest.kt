package fr.benguiza.selogertest.test


import android.content.Context
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import fr.benguiza.commons_android.BuildConfig
import fr.benguiza.commons_android.TSchedulers
import fr.benguiza.selogertest.app.SeLogerApplication
import fr.benguiza.selogertest.di.DaggerTAppComponent
import fr.benguiza.selogertest.di.modules.AppModule
import org.junit.Before
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
open class BaseTest {

    val defaultContext: Context
        get() = androidx.test.platform.app.InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUpBeforeTest() {
        TSchedulers.init()
    }

}


