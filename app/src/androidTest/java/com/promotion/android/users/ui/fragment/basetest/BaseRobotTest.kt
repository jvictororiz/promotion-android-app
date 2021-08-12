package com.promotion.android.users.ui.fragment.basetest

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.promotion.android.users.ui.activity.UserActivity
import okhttp3.mockwebserver.*
import org.koin.test.KoinTest

open class BaseRobotTest<T : AppCompatActivity> : KoinTest {

    protected lateinit var scenario: ActivityScenario<UserActivity>
    protected val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
    private val mockWebServer by lazy { MockWebServer() }

    fun startServer() {
        mockWebServer.start(8080)
    }

    fun closeActivity() {
        mockWebServer.shutdown()
        scenario.close()
    }

    protected fun prepareSuccess(pathResponse: String) {
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse()
                    .setResponseCode(200)
                    .setBody(readJson(pathResponse))
            }
        }
    }

    protected fun prepareError(codeError: Int = 500, noConnection: Boolean = false) {
        if(noConnection){
            mockWebServer.shutdown()
            return
        }
        mockWebServer.dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                return MockResponse().setResponseCode(codeError)
            }
        }
    }

    fun readJson(path: String): String =
        this@BaseRobotTest::class.java.getResourceAsStream(path)?.readBytes()
            ?.toString(Charsets.UTF_8) ?: ""
}