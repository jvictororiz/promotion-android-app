package com.promotion.android.basetest

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

private val trampoline = Schedulers.trampoline()

class RxSchedulerRule : TestRule {
    override fun apply(base: Statement, description: Description) =
        object : Statement() {
            override fun evaluate() {
                RxAndroidPlugins.setInitMainThreadSchedulerHandler { trampoline }
                RxJavaPlugins.setIoSchedulerHandler { trampoline }
                RxJavaPlugins.setNewThreadSchedulerHandler { trampoline }
                RxJavaPlugins.setComputationSchedulerHandler { trampoline }

                base.evaluate()
            }
        }
}