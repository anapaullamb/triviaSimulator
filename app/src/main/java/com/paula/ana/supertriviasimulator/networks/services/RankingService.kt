package com.paula.ana.supertriviasimulator.networks.services

import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import com.paula.ana.supertriviasimulator.models.Ranking.RankingReturn
import retrofit2.Call
import retrofit2.http.*

interface RankingService {
    @GET("ranking")
    fun getAll(): Call<RankingReturn>
}