package com.ngliaxl.retrofit.api

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/***
 *
 *
 *
 *
 *
 */
fun main() {
    val holder = Example01()
    val service = holder.retrofit.create(Example01.RetrofitService::class.java)
    val call = service.get()
    call.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            println("NORMAL GET ${response.body()?.string()}")
        }
    })


    val call2 = service.getJoke("28654780")
    call2.enqueue(object : Callback<ResponseBody> {
        override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
            t.printStackTrace()
        }

        override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
            println("NORMAL GET Query ${response.body()?.string()}")
        }
    })


}

class Example01 {
    val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.apiopen.top/")
            .build()
    }


    interface RetrofitService {

        @GET("getAllUrl")
        fun get(): Call<ResponseBody>

        /**
         * https://api.apiopen.top/getSingleJoke?sid=123
         */
        @GET("getSingleJoke")
        fun getJoke(@Query("sid") sid: String): Call<ResponseBody>

        /**
         * https://api.apiopen.top/getSingleJoke/123
         */
        @GET("getSingleJoke/{sid}")
        fun getJoke2(@Path("sid") sid: String): Call<ResponseBody>

    }
}

