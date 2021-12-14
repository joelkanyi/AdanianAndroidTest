package com.kanyideveloper.adanianandroidtest

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kanyideveloper.adanianandroidtest.presentation.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ImagesFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun recyclerview_is_showing() {
        Espresso.onView(withId(R.id.imagesRecyclerview))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun searchView_is_showing() {
        Espresso.onView(withId(R.id.searchView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}