package com.promotion.android.login.data.local

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.promotion.android.login.domain.exception.DefaultException
import io.reactivex.Completable
import io.reactivex.Single
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class UserLocalDataSourceImplTest {

    private val userDao: br.com.promotion.core.dao.UserDao = mock()
    private val localDataSource = br.com.common.login.data.local.UserLocalDataSourceImpl(userDao)

    @Test
    fun `when call getLocalUsers with success then return dao success`() {
        val expected = mockUsersDBResponse()
        whenever(userDao.getAll()).then { Single.just(expected) }

        localDataSource.getLocalUsers()
            .test()
            .assertNoErrors()
            .assertValue(expected)
            .assertComplete()
            .dispose()
    }

    @Test
    fun `when call saveLocalUsers with success then return dao success`() {
        val expected = mockUsersDBResponse()
        whenever(userDao.saveAll(expected)).then { Completable.complete() }

        localDataSource.saveLocalUsers(expected)
            .test()
            .assertNoErrors()
            .assertComplete()
            .assertOf { verify(userDao).saveAll(mockUsersDBResponse()) }
            .dispose()
    }

    @Test
    fun `when call saveLocalUsers with error then return defaultException`() {
        val expected = "Erro"
        whenever(userDao.saveAll(mockUsersDBResponse())).then { Completable.error(Exception(expected)) }

        localDataSource.saveLocalUsers(mockUsersDBResponse())
            .test()
            .assertError(DefaultException(expected))
            .assertNotComplete()
            .assertOf { verify(userDao).saveAll(mockUsersDBResponse()) }
            .dispose()
    }

    @Test
    fun `when call getLocalUsers with error then return defaultException`() {
        val expected = "Erro"
        whenever(userDao.getAll()).then { Single.error<List<br.com.promotion.core.entity.UserDB>>(Exception(expected)) }

        localDataSource.getLocalUsers()
            .test()
            .assertError(DefaultException(expected))
            .assertNotComplete()
            .assertOf { verify(userDao).getAll() }
            .dispose()
    }

    private fun mockUsersDBResponse() = listOf(
        br.com.promotion.core.entity.UserDB(
            "img",
            "name",
            1,
            "username"
        )
    )
}