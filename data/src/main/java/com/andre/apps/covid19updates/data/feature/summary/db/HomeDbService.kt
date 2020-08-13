package com.andre.apps.covid19updates.data.feature.summary.db

import com.andre.apps.covid19updates.data.base.LocalDb
import com.andre.apps.covid19updates.data.feature.summary.entity.CountryItemEntity
import com.andre.apps.covid19updates.data.feature.summary.entity.HomeEntity
import com.andre.apps.covid19updates.data.feature.summary.entity.HomeEntity_
import io.objectbox.Box
import io.objectbox.kotlin.boxFor
import javax.inject.Inject

class HomeDbService @Inject constructor(private val localDb: LocalDb) {

    suspend fun getSavedGlobalSummary(): HomeEntity? {

        return localDb.read {
            val box: Box<HomeEntity> = it.boxFor()
            return@read box.query().build().findFirst()
        }
    }

    suspend fun saveGlobalSummary(data: HomeEntity) {

        return localDb.write {
            val box: Box<HomeEntity> = it.boxFor()
            val entityQuery = box.query()
            val entity = entityQuery.build().findFirst()
            if (entity == null) {
                box.put(data)
            } else {
                val countriesBox: Box<CountryItemEntity> = it.boxFor()
                countriesBox.removeAll()

                box.put(data)
            }
        }
    }

    suspend fun getCountrySummary(): List<CountryItemEntity>? {

        return localDb.read {
            val box: Box<HomeEntity> = it.boxFor()
            val builder = box.query()
            builder.link(HomeEntity_.countries)
            val entity = builder.build().findFirst()
            val li = entity?.countries?.toList()
            return@read li ?: return@read null
        }
    }
}
