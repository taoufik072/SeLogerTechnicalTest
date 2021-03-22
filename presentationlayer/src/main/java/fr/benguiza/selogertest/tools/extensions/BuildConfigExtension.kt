package fr.benguiza.selogertest.tools.extensions

import fr.benguiza.commons_android.BuildConfig


object AppBuildConfig {
    fun getTestingValue(): Boolean {
        return BuildConfig.IS_TESTING
    }
}