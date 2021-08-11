package com.example.mycontactapp.ContactToDos.ContactViews

import android.Manifest
import android.support.test.uiautomator.UiDevice
import android.support.test.uiautomator.UiSelector
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.mycontactapp.ContactDetails
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ContactDetailsUITest {

    private var device: UiDevice? = null


    @get:Rule
    var activityRule: ActivityScenarioRule<ContactDetails>
            = ActivityScenarioRule(ContactDetails::class.java)



        @Before
        fun setUp() {
            this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        }

        @Test
        fun testFeedbackCallPermissionDenied() {
            val denyButton = this.device?.findObject(UiSelector().text("DENY"))
            val permissionDeniedMessage =
                this.device?.findObject(UiSelector().text("Permission denied"))

            denyButton!!.click()

            assert(permissionDeniedMessage!!.exists())
        }

        @Test
        fun testFeedbackCallPermissionAllowed() {
            val allowButton = this.device?.findObject(UiSelector().text("ALLOW"))
            var permissionAllowedMessage =
                this.device?.findObject(UiSelector().text("Permission allowed"))
            allowButton!!.click()
            assert(permissionAllowedMessage!!.exists())
        }

    @After
    fun tearDown() {
        InstrumentationRegistry.getInstrumentation().uiAutomation.revokeRuntimePermission(
            InstrumentationRegistry.getInstrumentation().targetContext.packageName,
            Manifest.permission.CALL_PHONE)
    }

}
