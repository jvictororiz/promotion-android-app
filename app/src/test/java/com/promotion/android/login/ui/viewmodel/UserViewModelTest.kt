package com.promotion.android.login.ui.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.promotion.android.base.di.builders.ResourceManager
import com.promotion.android.basetest.BaseViewModelTest
import com.promotion.android.basetest.RxSchedulerRule
import com.promotion.android.basetest.TestStateLiveData
import com.promotion.android.login.domain.model.User
import com.promotion.android.login.domain.usecase.contract.UserUseCase
import com.promotion.android.login.ui.viewmodel.model.UserState
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserViewModelTest : BaseViewModelTest<UserState>() {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var schedulers = RxSchedulerRule()


    private val useCase: UserUseCase = mock()
    private val resourceManager: ResourceManager = mock()

    private lateinit var viewModel: UserViewModel
    private var stateLiveData = TestStateLiveData<UserState>()

    @Before
    fun init() {
        setupState(stateLiveData)
    }

    @After
    fun after() {
        stateLiveData.clear()
    }


    @Test
    fun `getUser with success in network`() {
        val expectedResponse = mockUsersResponse()
        whenever(useCase.getAll()).thenReturn(Single.just(expectedResponse))

        viewModel = UserViewModel(resourceManager, useCase, stateLiveData)

        verifyOrder(
            UserState.LoadingState,
            UserState.SuccessState(expectedResponse)
        )
    }

    @Test
    fun `getUser with error in network`() {
        val expectedString1 = "teste"
        val expectedString2 = "teste2"
        whenever(resourceManager.message(any()))
            .thenReturn(expectedString1)
            .thenReturn(expectedString2)
        whenever(useCase.getAll()).thenReturn(Single.error(Exception()))
        whenever(useCase.getAllLocal()).thenReturn(Single.just(listOf()))

        viewModel = UserViewModel(resourceManager, useCase, stateLiveData)

        verifyOrder(
            UserState.LoadingState,
            UserState.ErrorState(expectedString1, expectedString2),
            UserState.SuccessLocalState(listOf())
        )
    }

    @Test
    fun `getUser with success with error in remote and without cache`() {
        val expectedString1 = "teste"
        val expectedString2 = "teste2"
        whenever(resourceManager.message(any()))
            .thenReturn(expectedString1)
            .thenReturn(expectedString2)
        whenever(useCase.getAll()).thenReturn(Single.error(Exception()))
        whenever(useCase.getAllLocal()).thenReturn(Single.just(listOf()))

        viewModel = UserViewModel(resourceManager, useCase, stateLiveData)

        verifyOrder(
            UserState.LoadingState,
            UserState.ErrorState(
                expectedString1,
                expectedString2
            ),
            UserState.SuccessLocalState(listOf())
        )
    }

    @Test
    fun `getUser with error in network and with cache`() {
        val expectedString1 = "teste"
        val expectedString2 = "teste2"
        whenever(resourceManager.message(any()))
            .thenReturn(expectedString1)
            .thenReturn(expectedString2)
        whenever(useCase.getAll()).thenReturn(Single.error(Exception()))
        whenever(useCase.getAllLocal()).thenReturn(Single.just(mockUsersResponse()))

        viewModel = UserViewModel(resourceManager, useCase, stateLiveData)

        verifyOrder(
            UserState.LoadingState,
            UserState.ErrorState(expectedString1, expectedString2),
            UserState.SuccessLocalState(mockUsersResponse()))
    }

    private fun mockUsersResponse() = listOf(User("img", "name", 1, "username"))

}
