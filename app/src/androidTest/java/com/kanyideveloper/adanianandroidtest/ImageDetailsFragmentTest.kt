package com.kanyideveloper.adanianandroidtest

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kanyideveloper.adanianandroidtest.presentation.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageDetailsFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)
    
    @Before
    fun navigateToThisFragment() {
        Thread.sleep(5000)

        val recyclerView = onView(
            allOf(
                withId(R.id.imagesRecyclerview)
            )
        )
        recyclerView.perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
    }

    @Test
    fun toolbar_is_showing() {
        onView(withId(R.id.toolbar))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun username_is_showing() {
        onView(withId(R.id.textViewUserName))
            .check(ViewAssertions.matches(isDisplayed()))
    }

    @Test
    fun image_is_showing() {
        onView(withId(R.id.imageViewUser))
            .check(ViewAssertions.matches(isDisplayed()))
    }
}