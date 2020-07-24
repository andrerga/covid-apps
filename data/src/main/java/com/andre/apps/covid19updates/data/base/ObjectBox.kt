package com.andre.apps.covid19updates.data.base

import android.content.Context
import com.andre.apps.covid19updates.data.feature.summary.entity.MyObjectBox
import io.objectbox.BoxStore

class ObjectBox {

    companion object {

        lateinit var boxStore: BoxStore
            private set

        fun init(context: Context) {
            boxStore = MyObjectBox.builder()
                .androidContext(context.applicationContext)
                .build()
        }
    }
}