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
    val holder = Example03()
    val service = holder.retrofit.create(Example03.RetrofitService::class.java)


    val call = service.post(mapOf("key" to "RMB"))
    call.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            println(response.body()?.string())
        }
    })
}

class Example03 {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://op.juhe.cn/")
            .build()
    }


    interface RetrofitService {

        @FormUrlEncoded
        @POST("onebox/exchange/query")
        fun post(@FieldMap map: Map<String, String>): Call<ResponseBody>


    }

}

