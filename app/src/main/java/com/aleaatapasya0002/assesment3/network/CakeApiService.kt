package com.aleaatapasya0002.assesment3.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://store.sthresearch.site/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface CakeApiService {
    @GET("cake.php")
    suspend fun getCake(): String
}
object CakeApi{
    val service: CakeApiService by lazy{
        retrofit.create(CakeApiService::class.java)
    }
}