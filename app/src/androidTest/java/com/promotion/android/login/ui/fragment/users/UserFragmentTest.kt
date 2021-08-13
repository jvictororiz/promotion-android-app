package com.promotion.android.login.ui.fragment.users

import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class UserFragmentTest {

    private val robot by lazy { UserFragmentRobot.instance() }

    @Test
    fun getUsersWithSuccess() {
        robot
            .startTest()
            .injectUsersSuccessMock()
            .launchActivity()
            .validateTitle()
            .validateUsersScreen()
            .validateNotContainsErrorScreen()
            .endTest()
    }

    @Test
    fun getUsersWithErrorConnection() {
        robot
            .startTest()
            .injectUsersErrorNoConnectionMock()
            .launchActivity()
            .validateTitle()
            .validateContainsErrorScreen()
            .validateNoConnectionMessageError()
            .endTest()
    }

    @Test
    fun getUsersWithErrorServer() {
        robot
            .startTest()
            .injectUsersErrorServerMock()
            .launchActivity()
            .validateTitle()
            .validateContainsErrorScreen()
            .validateServerMessageError()
            .validateEmptyUsersScreen()
            .endTest()
    }

    @Test
    fun getUsersWithErrorAndGetLocalBase() {
        robot
            .startTest()
            .injectUsersErrorServerMock()
            .injectUsersLocalDatabase()
            .launchActivity()
            .validateTitle()
            .validateContainsErrorScreen()
            .validateServerMessageError()
            .validateUsersScreen()
            .endTest()
    }

    @Test
    fun getUsersWithError_AfterSwipeAndUpdateWithConnection() {
        robot
            .startTest()
            .injectUsersErrorServerMock()
            .launchActivity()
            .validateTitle()
            .validateContainsErrorScreen()
            .injectUsersSuccessMock()
            .swipeRefreshLoad()
            .validateUsersScreen()
            .endTest()
    }

    @Test
    fun getUsersWithError_AfterRetryAndUpdateWithConnection() {
        robot
            .startTest()
            .injectUsersErrorServerMock()
            .launchActivity()
            .validateTitle()
            .validateContainsErrorScreen()
            .injectUsersSuccessMock()
            .tapOnRetry()
            .validateUsersScreen()
            .endTest()
    }
}