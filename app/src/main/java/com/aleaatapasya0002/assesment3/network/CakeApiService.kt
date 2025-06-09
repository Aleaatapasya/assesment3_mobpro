package com.aleaatapasya0002.assesment3.network

import com.aleaatapasya0002.assesment3.model.Cake
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit.Builder
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://store.sthresearch.site/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()
private val retrofit = Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CakeApiService {
    @GET("cake.php")
    suspend fun getCake(): List<Cake>
}
object CakeApi{
    val service: CakeApiService by lazy{
        retrofit.create(CakeApiService::class.java)
    }

    fun getCakeUrl(imageId: String): String{
        return "$BASE_URL$imageId.jpg"
    }

    enum class ApiStatus { LOADING, SUCCESS}
}