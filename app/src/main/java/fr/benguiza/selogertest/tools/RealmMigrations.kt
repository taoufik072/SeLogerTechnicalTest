package fr.benguiza.selogertest.tools

import android.content.Context
import io.realm.DynamicRealm
import io.realm.RealmMigration
//TODO add when passing to next version
class RealmMigrations(val context: Context) : RealmMigration {

    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val realmSchema = realm.schema
        var version = oldVersion
        if (version == 1L) {
            version += 1
        }
    }
}