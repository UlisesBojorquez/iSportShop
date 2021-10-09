package com.example.isportshop

import android.app.Activity
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class RegisterActivityTest{

    @get: Rule
    val activityRule = ActivityScenarioRule(RegisterActivity::class.java)

    /**
     * Test the activity is launched correctly in the view
     */
    @Test
    fun test_isActivityInView(){
        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }
    /**
     * Test of visibility of the components inside the current activity (RegisterActivity)
     */
    @Test
    fun test_visibility_components_RegisterActivity(){
        onView(withId(R.id.titleRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.labelRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.labelAlreadyHaveAccount)).check(matches(isDisplayed()))
        onView(withId(R.id.linkSignIn)).check(matches(isDisplayed()))
        onView(withId(R.id.btnSignUp)).check(matches(isDisplayed()))
        onView(withId(R.id.txtNameRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.txtLastNameRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.txtEmailRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.txtPasswordRegister)).check(matches(isDisplayed()))
        onView(withId(R.id.txtPasswordRegister2)).check(matches(isDisplayed()))
    }
}