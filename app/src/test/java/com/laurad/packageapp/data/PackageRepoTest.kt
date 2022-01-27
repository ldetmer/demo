package com.laurad.packageapp.data

import com.laurad.packageapp.model.ApkInfo
import com.laurad.packageapp.model.PackageDao
import com.laurad.packageapp.model.PackageInfo
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.doThrow
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException

@RunWith(MockitoJUnitRunner::class)
class PackageRepoTest {

    val packageName = "package"

    val matchingPackage = PackageInfo(packageName, "", "")

    val packageApiResponse = ApkInfo(listOf(matchingPackage))

    private val mockApi = mock<Api>()
    private val mockPackageDao = mock<PackageDao> {
        on { lookupPackageInfoByName(packageName) } doReturn (matchingPackage)
    }

    private val mockDb = mock<AppDatabase> {
        on { packageDao() } doReturn mockPackageDao
    }

    private val subject = PackageRepo(mockApi, mockDb)

    @Test
    fun retrievePackageFromDBIfCantFindInMemory() {
        val result = subject.lookUpPackage(packageName)
        Assert.assertEquals(result, matchingPackage)
    }

    @Test
    fun retrievePackageFromMemoryIfExists() {
        subject.packages = listOf(matchingPackage)
        val result = subject.lookUpPackage(packageName)
        verify(mockPackageDao, Mockito.times(0)).lookupPackageInfoByName(packageName)
        Assert.assertEquals(result, matchingPackage)
    }

    @ExperimentalCoroutinesApi
    @Test
     fun successfulRetrievalOfPackagesUpdatesDB() = runBlockingTest {
        whenever(mockApi.getPackages(API_ENDPOINT_LATEST)) doReturn packageApiResponse

        val result = subject.getPackages()

        verify(mockPackageDao, Mockito.times(2)).deleteAll()
        verify(mockPackageDao, Mockito.times(1)).insertAll(result)
        Assert.assertEquals(result.get(0), matchingPackage)
        Assert.assertTrue(result.size == 1)
    }

   @Test
    fun errorOnRetrievalOfPackagesRetrievesFromDB() = runBlockingTest {
       val mockErrorResponse = mock<HttpException>()
       whenever(mockApi.getPackages(API_ENDPOINT_LATEST)) doThrow mockErrorResponse
       whenever(mockPackageDao.getAll()) doReturn listOf(matchingPackage)

       val result = subject.getPackages()

       verify(mockPackageDao, Mockito.times(1)).getAll()
       Assert.assertEquals(result.get(0), matchingPackage)
       Assert.assertTrue(result.size == 1)
   }
}