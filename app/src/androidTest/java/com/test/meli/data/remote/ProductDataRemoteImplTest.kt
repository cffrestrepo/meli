package com.test.meli.data.remote

import com.test.meli.PERRO
import com.test.meli.commons.Either
import com.test.meli.createLooFor
import com.test.meli.data.network.ErrorFactory
import com.test.meli.data.remote.response.LookFor
import io.mockk.*
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/***
 * Test class of [ProductDataRemoteImpl]
 */
@ExperimentalCoroutinesApi
class ProductDataRemoteImplTest {

    @MockK
    private lateinit var retrofitServicesInterface: RetrofitServicesInterface

    @MockK
    private lateinit var errorFactory: ErrorFactory

    private lateinit var productDataRemoteImpl: ProductDataRemoteImpl

    @Before
    fun setUp() {
        MockKAnnotations.init(
            this,
            relaxUnitFun = true
        )
        productDataRemoteImpl = ProductDataRemoteImpl(retrofitServicesInterface, errorFactory)
    }

    private fun verifyAllMocks() {
        confirmVerified(
            retrofitServicesInterface,
            errorFactory
        )
    }

    @After
    fun tearDown() = clearAllMocks()

    @Test
    fun `Given-a-call-to-getProductsBySearch-When-this-answer-is-correct-Then-it-gives-us-a-Either-Right-of-type-LookFor-model`() =
        runBlocking {
            // GIVEN
            val looFor = createLooFor()
            val query = PERRO
            val mockedCall = mockk<retrofit2.Call<LookFor>>()
            val enqueue = object : Callback<LookFor> {
                override fun onResponse(call: Call<LookFor>, response: Response<LookFor>) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(call: Call<LookFor>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            }

            coEvery {
                retrofitServicesInterface.getProductsBySearch(query)!!.enqueue(enqueue)
            } answers {
                val callback = args[0] as retrofit2.Callback<LookFor>
                val response = retrofit2.Response.success(200, looFor)

                callback.onResponse(mockedCall, response)
            }
            /*
            val deliveryAcceptedDetailsCallbackCaptor =
                CapturingSlot<Callback<DeliveryAcceptedDetailResponse?>>()

             */

            // WHEN
            val result = productDataRemoteImpl.getProductsBySearch(query)

            // VERIFY
            assert(result is Either.Right)
        }
}
