package com.aleaatapasya0002.assesment3.network

import com.aleaatapasya0002.assesment3.model.Cake
import com.aleaatapasya0002.assesment3.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query


private const val BASE_URL = "https://store.sthresearch.site/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface CakeApiService {
    @GET("cake.php")
    suspend fun getCake(
        @Header("Authorization") userId: String
    ): List<Cake>

    @Multipart
    @POST("cake.php")
    suspend fun postCake(
        @Header("Authorization") userId: String,
        @Part("namaKue") namaKue: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part,
    ): OpStatus

    @Multipart
    @POST("cake.php")
    suspend fun updateCake(
        @Header("Authorization") userId: String,
        @Part("id") id: RequestBody,
        @Part("namaKue") namaKue: RequestBody,
        @Part("harga") harga: RequestBody
    ): OpStatus

    @Multipart
    @POST("cake.php")
    suspend fun updateImage(
        @Header("Authorization") userId: String,
        @Part("id") id: RequestBody,
        @Part("namaKue") namaKue: RequestBody,
        @Part("harga") harga: RequestBody,
        @Part image: MultipartBody.Part,
    ): OpStatus

    @DELETE("cake.php")
    suspend fun deleteCake(
        @Header("Authorization") userId: String,
        @Query("id") idCake: String,
    ): OpStatus
}

object CakeApi{
    val service: CakeApiService by lazy {
        retrofit.create(CakeApiService::class.java)
    }
    fun getCakeUrl(imageId: String):String{
        return "${BASE_URL}image.php?id=$imageId"
    }
}
enum class ApiStatus { LOADING, SUCCESS,FAILED}