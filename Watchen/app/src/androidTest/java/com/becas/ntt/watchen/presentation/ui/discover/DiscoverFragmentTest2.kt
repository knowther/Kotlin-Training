package com.becas.ntt.watchen.presentation.ui.discover

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.becas.ntt.watchen.presentation.ui.MainActivity
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiscoverFragmentTest2{

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun Should_open_movie(){
        ActivityScenario.launch(MainActivity::class.java)
        Espresso.onView(ViewMatchers.withText("Discover"))
    }
}