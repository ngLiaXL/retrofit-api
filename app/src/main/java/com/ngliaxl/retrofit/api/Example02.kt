package com.ngliaxl.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

fun main() {
    val holder = Example02()
    val service = holder.retrofit.create(Example02.RetrofitService::class.java)

    val call = service.post(mapOf("page" to "1","count" to "2"))
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


    }

}

