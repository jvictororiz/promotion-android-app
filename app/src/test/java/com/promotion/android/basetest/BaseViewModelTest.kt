package com.promotion.android.basetest

import com.promotion.android.login.viewmodel.model.UserState

open class BaseViewModelTest<T> {

    private var testStateLiveData: TestStateLiveData<T>? = null

    fun setupState(testStateLiveData: TestStateLiveData<T>) {
        this.testStateLiveData = testStateLiveData
    }

    protected fun verifyOrder(vararg statesExpected: UserState) {
        val statesCalled = testStateLiveData?.getListStates()
            ?: throw ViewModelTestException("stateLiveData == null, It is necessary to call setupState(livedata) ")
        if (statesExpected.size != statesCalled.size) {
            throw ViewModelTestException("\nReal calls have " + statesCalled.size + " calls, while the expected calls have " + statesExpected.size)
        }
        var logError = String()
        statesCalled.forEachIndexed { index, stateCalled ->
            val stateExpected = statesExpected[index]
            val assert = stateExpected == stateCalled
            if (assert) {
                logError += "Success: expected:$stateExpected   -   called:$stateCalled\n"
            } else {
                logError += "Error: expected:($stateExpected)   -   called:$stateCalled"
                throw ViewModelTestException(logError)
            }
        }
    }
}