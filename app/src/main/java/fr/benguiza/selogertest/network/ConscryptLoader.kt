package fr.benguiza.selogertest.network

import androidx.annotation.NonNull
import fr.benguiza.commons.tools.Logger
import org.conscrypt.Conscrypt
import java.security.Provider
import java.security.Security
import java.util.*

final class ConscryptLoader {

    private var installed = false

    private fun ConscryptLoader() {}

    fun load() {
        if (!installed) {
            synchronized(Security::class.java) { synchronized(Conscrypt::class.java) { installConscrypt() } }
        }
    }

    private fun installConscrypt() {
        // If conscrypt isn't available, we're done. Nothing to install.
        if (!Conscrypt.isAvailable()) {
            return
        }
        // Remove any existing installations.
        for (provider in findInstalledConscryptProviders()) {
            Security.removeProvider(provider.name)
        }
        // Now, install Conscrypt.
        Security.insertProviderAt(Conscrypt.newProvider(), 1)
        installed = true
        Logger.log("Installed Conscrypt security provider.")
    }

    @NonNull
    private fun findInstalledConscryptProviders(): kotlin.collections.List<Provider> {
        val conscrypts: MutableList<Provider> = ArrayList()
        for (provider in Security.getProviders()) {
            if (Conscrypt.isConscrypt(provider)) {
                conscrypts.add(provider)
            }
        }
        return conscrypts
    }
}
