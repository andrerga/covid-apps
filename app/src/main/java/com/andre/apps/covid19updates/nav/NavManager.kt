package com.andre.apps.covid19updates.nav

import androidx.navigation.NavArgs
import androidx.navigation.NavDirections
import androidx.navigation.Navigator

class NavManager {

    private var navEventListener: ((navDirections: NavDirections, extras: Navigator.Extras?) -> Unit)? = null
    private var argsEventListener: (() -> NavArgs?)? = null

    fun navigate(navDirections: NavDirections) {
        navEventListener?.invoke(navDirections, null)
    }

    fun navigate(navDirections: NavDirections, extras: Navigator.Extras) {
        navEventListener?.invoke(navDirections, extras)
    }

    fun getArgs(): NavArgs? = argsEventListener?.invoke()

    fun setOnNavEvent(navEventListener: (navDirections: NavDirections, extras: Navigator.Extras?) -> Unit) {
        this.navEventListener = navEventListener
    }

    fun setArgsEvent(eventListener: () -> NavArgs?) {
        this.argsEventListener = eventListener
    }
}