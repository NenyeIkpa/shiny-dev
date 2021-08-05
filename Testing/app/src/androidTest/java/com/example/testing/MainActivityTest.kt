package com.example.testing

import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matchers.containsString
import org.hamcrest.core.IsAnything.anything
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class MainActivityTest {

    private lateinit var stringToBeTypedName: String
    private lateinit var stringToBeTypedPhoneNum: String
    private lateinit var stringToBeTypedEmail: String
    private lateinit var stringToBeTypedPassword: String
    private lateinit var stringToBeTypedConfirmPassword: String

    @get:Rule
    var activityRule: ActivityScenarioRule<MainActivity>
            = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun initValidString() {
        // Specify a valid string.
        stringToBeTypedName = "Li Wong"
        stringToBeTypedPhoneNum = "2348063492057"
        stringToBeTypedEmail = "123@abc.go"
        stringToBeTypedPassword = "android@123"
        stringToBeTypedConfirmPassword = "android@123"
    }

    @Test
    fun inputValidText_Validate_And_PerformClick() {

        onView(withId(R.id.fullName_et))
            .perform(ViewActions.typeText(stringToBeTypedName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.phoneNum_et))
            .perform(ViewActions.typeText(stringToBeTypedPhoneNum), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.email_et))
            .perform(ViewActions.typeText(stringToBeTypedEmail), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password_et))
            .perform(ViewActions.typeText(stringToBeTypedPassword), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.confirmPassword_et))
            .perform(ViewActions.typeText(stringToBeTypedConfirmPassword), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())


    }


    @Test
    fun inputTextInstances_Validate_And_PerformClick() {

        onView(withId(R.id.fullName_et))
            .perform(ViewActions.typeText("Li"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.fullName_et))
            .perform(ViewActions.replaceText(stringToBeTypedName), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.phoneNum_et))
            .perform(ViewActions.typeText("19012346782"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.phoneNum_et))
            .perform(ViewActions.replaceText(stringToBeTypedPhoneNum), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.email_et))
            .perform(ViewActions.typeText("android@123.com"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.email_et))
            .perform(ViewActions.replaceText(stringToBeTypedEmail), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.password_et))
            .perform(ViewActions.typeText("android123"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.password_et))
            .perform(ViewActions.replaceText(stringToBeTypedPassword), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.confirmPassword_et))
            .perform(ViewActions.typeText("abc@123"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
        onView(withId(R.id.confirmPassword_et))
            .perform(
                ViewActions.replaceText(stringToBeTypedConfirmPassword),
                ViewActions.closeSoftKeyboard()
            )
        onView(withId(R.id.registerButton)).perform(ViewActions.click())
    }

        @Test
        fun checkIfText_Matches_SpinnerValue() {

        onView(withId(R.id.spinner)).perform(click());
        onData(anything()).atPosition(0).perform(click());
        onView(withId(R.id.spinner)).check(matches(withSpinnerText(containsString("Male"))))
    }



        @Test
        fun passwordConfirmationCheck() {
            onView(withId(R.id.password_et))
                .perform(ViewActions.typeText("android123"), ViewActions.closeSoftKeyboard())
            onView(withId(R.id.registerButton)).perform(ViewActions.click())
            onView(withId(R.id.password_et))
                .perform(ViewActions.replaceText(stringToBeTypedPassword), ViewActions.closeSoftKeyboard())
            onView(withId(R.id.registerButton)).perform(ViewActions.click())
            onView(withId(R.id.confirmPassword_et))
                .perform(ViewActions.typeText("abc@123"), ViewActions.closeSoftKeyboard())
            onView(withId(R.id.registerButton)).perform(ViewActions.click())
            onView(withId(R.id.confirmPassword_et))
                .perform(
                    ViewActions.replaceText(stringToBeTypedConfirmPassword),
                    ViewActions.closeSoftKeyboard()
                )
            onView(withId(R.id.registerButton)).perform(ViewActions.click())
    }


    }