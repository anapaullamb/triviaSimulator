 package com.paula.ana.supertriviasimulator.dao

import android.util.Log
import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import com.paula.ana.supertriviasimulator.models.Ranking.RankingReturn
import com.paula.ana.supertriviasimulator.networks.services.CategoriaService
import com.paula.ana.supertriviasimulator.networks.services.RankingService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RankingDAO {
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();
    val service = retrofit.create(RankingService::class.java);

    fun getAll(finished: (ranking: RankingReturn) -> Unit) {
        service.getAll().enqueue(object : Callback<RankingReturn> {

            override fun onResponse(call: Call<RankingReturn>, response: Response<RankingReturn>) {
                finished(response.body()!!)
            }
            override fun onFailure(call: Call<RankingReturn>, t: Throwable) {
                Log.i("RankingDAO", "falhoo "+t)}
            }
        )
    }
}