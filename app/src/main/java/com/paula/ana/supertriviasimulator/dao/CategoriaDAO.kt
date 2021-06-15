 package com.paula.ana.supertriviasimulator.dao

import android.util.Log
import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import com.paula.ana.supertriviasimulator.networks.services.CategoriaService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CategoriaDAO {
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
    val service = retrofit.create(CategoriaService::class.java);

    fun getAll(finished: (categories: CategoriaReturn) -> Unit) {
        service.getAll().enqueue(object : Callback<CategoriaReturn> {

            override fun onResponse(call: Call<CategoriaReturn>, response: Response<CategoriaReturn>) {
                finished(response.body()!!)
            }
            override fun onFailure(call: Call<CategoriaReturn>, t: Throwable) {
                Log.i("CategoriaDAO", "falhoo "+t)}
            }
        )
    }
}