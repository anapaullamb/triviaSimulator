package com.paula.ana.supertriviasimulator.networks.services

import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import retrofit2.Call
import retrofit2.http.*

interface CategoriaService {
    @GET("categories")
    fun getAll(): Call<CategoriaReturn>
}