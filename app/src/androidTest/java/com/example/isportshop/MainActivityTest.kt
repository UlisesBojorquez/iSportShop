package com.example.isportshop

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest{
    /**
     * Test the activity is launched correctly in the view
     */
    @Test
    fun test_isActivityInView(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.login)).check(matches(isDisplayed()))
    }
    /**
     * Test of visibility of the components inside the current activity (MainActivity)
     */
    @Test
    fun test_visibility_components_MainActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.titleLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.txtEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.txtPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.linkRecoverPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
        onView(withId(R.id.labelHaveAnAccount)).check(matches(isDisplayed())) //Method #1
        onView(withId(R.id.linkSignUp)).check(matches(withEffectiveVisibility(Visibility.VISIBLE))) //Method #2
    }
    /**
     * Test navigation to the RegisterActivity on button click
     */
    @Test
    fun test_navigation_to_RegisterActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.linkSignUp)).perform(click()) //Perform click on the button
        onView(withId(R.id.register)).check(matches(isDisplayed()))
    }
    /**
     * Test navigation from RegisterActivity to MainActivity on button click
     */
    @Test
    fun test_navigation_from_RegisterActivity_to_MainActivity(){
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.linkSignUp)).perform(click()) //Perform click on the button
        onView(withId(R.id.register)).check(matches(isDisplayed()))
        //onView(withId(R.id.linkSignIn)).perform(click()) //Method #1 to go back to MainActivity
        pressBack() //Method #2 to go back to MainActivity
        onView(withId(R.id.login)).check(matches(isDisplayed()))


    }


}