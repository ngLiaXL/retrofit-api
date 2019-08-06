package com.ngliaxl.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.*

fun main() {
    val holder = Example02()
    val service = holder.retrofit.create(Example02.RetrofitService::class.java)

    val call = service.post(mapOf("page" to "1", "count" to "2"))
    call.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            println(response.body()?.string())
        }
    })
}

class Example02 {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.apiopen.top/")
            .build()
    }


    interface RetrofitService {

        @FormUrlEncoded
        @POST("getWangYiNews")
        fun post(@FieldMap map: Map<String, String>): Call<ResponseBody>

        /**
         * POST map
         * ?token={token}
         * 补全URL id
         */
        @FormUrlEncoded
        @POST("getWangYiNews/{id}")
        fun post2(
            @FieldMap map: Map<String, String>,
            @Path("id") id: Int, @Query("token") token: String
        ): Call<ResponseBody>


        /**
         * POST一个对象
         */
        @POST("getWangYiNews/{id}")
        fun post3(
            @Body user: User,
            @Path("id") id: Int, @Query("token") token: String
        ): Call<ResponseBody>

        /**
         * 用不同注解POST一个对象
         */
        @POST("getWangYiNews/{id}")
        fun post4(
            @Part("user") user: User,
            @Path("id") id: Int, @Query("token") token: String
        ): Call<ResponseBody>
    }


    class User
}

