package com.ngliaxl.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

// https://api.apiopen.top/EmailSearch?number=1012002
fun main() {
    val holder = Example01()
    val service = holder.retrofit.create(Example02.RetrofitService::class.java)
    val call = service.get("1012002")
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

        @GET("EmailSearch")
        fun get(@Query("number") number: String): Call<ResponseBody>


    }

}

