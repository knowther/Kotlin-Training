package com.becas.ntt.watchen.presentation.ui.discover

import androidx.lifecycle.ViewModelProvider
import androidx.test.core.app.ActivityScenario.*
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.becas.ntt.watchen.data.webclient.model.dto.MovieDTO
import com.becas.ntt.watchen.data.webclient.model.dto.Videos
import com.becas.ntt.watchen.presentation.ui.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DiscoverFragmentTest{


    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)
    @Test
    fun Should_open_movie(){
        launch(MainActivity::class.java)
        onView(withText("Discover"))
    }

    @Test
    fun testMovieList() {
        val movieList = listOf<MovieDTO>(
            MovieDTO(
                true,
                "",
                listOf(1),
                1,
                "",
                "",
                "",
                "",
                1.0,
                "",
                "",
                "MovieTest",
                false,
                1.0,
                Videos(listOf()),
                1

            )
        )


        runBlocking(Dispatchers.Main) {
            activityRule.scenario.onActivity {
                val viewModel = ViewModelProvider(
                    it,
                    ViewModelProvider.AndroidViewModelFactory.getInstance(it.application)
                ).get(DiscoverViewModel::class.java)



//                    viewModel._movieList.postValue(movieList)
//                    onView(withId(R.id.recycler_view_discover))
//                        .check(matches(hasDescendant(withText("MovieTest"))))


            }

        }

    }
}