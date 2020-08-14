package com.andre.apps.covid19updates.ui

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.setupWithNavController
import com.andre.apps.covid19updates.R
import com.andre.apps.covid19updates.databinding.MainActivityBinding
import com.andre.apps.covid19updates.nav.NavManager
import com.andre.apps.covid19updates.util.getNavArgsInstance
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navManager: NavManager

    private lateinit var binding: MainActivityBinding

    private lateinit var _navController: NavController
    private val navController: NavController get() = _navController

    private lateinit var _openAnimation: Animation
    private val openAnimation get() = _openAnimation

    private lateinit var _closeAnimation: Animation
    private val closeAnimation get() = _closeAnimation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _navController = Navigation.findNavController(this, R.id.container)

        initNavManager()
        handleActivity()
        initAnimation()
        setNavigation()
    }

    private fun initAnimation() {
        _openAnimation = AnimationUtils.loadAnimation(this, R.anim.fragment_open_enter)

        _closeAnimation = AnimationUtils.loadAnimation(this, R.anim.fragment_open_exit)
        _closeAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                binding.navBottom.visibility = View.GONE
            }

            override fun onAnimationStart(animation: Animation?) {}
        })
    }

    private fun initNavManager() {
        navManager.setOnNavEvent { target, extras ->
            if (extras != null) {
                navController.navigate(target, extras)
            } else {
                navController.navigate(target)
            }
        }
    }

    private fun handleActivity() {
        supportFragmentManager.registerFragmentLifecycleCallbacks(
            object : FragmentManager.FragmentLifecycleCallbacks() {
                override fun onFragmentCreated(
                    fm: FragmentManager,
                    f: Fragment,
                    savedInstanceState: Bundle?
                ) {
                    super.onFragmentCreated(fm, f, savedInstanceState)

                    navManager.setArgsEvent(f::getNavArgsInstance)
                }
            }, true)
    }

    private fun setNavigation() {
        binding.navBottom.setupWithNavController(navController)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.homeFragment -> showBottomNav()
                R.id.countryListFragment -> showBottomNav()
                R.id.newsFragment -> showBottomNav()
                else -> hideBottomNav()
            }
        }
    }

    private fun hideBottomNav() {
        binding.navBottom.run {
            if (visibility == View.VISIBLE) {
                startAnimation(closeAnimation)
            }
        }
    }

    private fun showBottomNav() {
        binding.navBottom.run {
            visibility = View.VISIBLE
            startAnimation(openAnimation)
        }
    }
}
