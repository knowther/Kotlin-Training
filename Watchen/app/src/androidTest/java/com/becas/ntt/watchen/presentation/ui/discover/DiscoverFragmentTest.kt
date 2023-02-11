package com.becas.ntt.watchen.presentation.ui.discover

import androidx.test.core.app.ActivityScenario.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.becas.ntt.watchen.presentation.ui.MainActivity
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiscoverFragmentTest{
    @Test
    fun Should_open_movie(){
        launch(MainActivity::class.java)
        onView(withText("Discover"))
    }
}