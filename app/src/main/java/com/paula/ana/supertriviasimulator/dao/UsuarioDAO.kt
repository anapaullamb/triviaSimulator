package com.paula.ana.supertriviasimulator.dao

import android.util.Log
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioLogar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioRegistrar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioReturn
import com.paula.ana.supertriviasimulator.networks.services.UsuarioService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class UsuarioDAO {
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

    val service = retrofit.create(UsuarioService::class.java);

    fun insert(usuario: UsuarioRegistrar, finished: (usuario: UsuarioReturn) -> Unit) {
        service.insert(usuario).enqueue(object : Callback<UsuarioReturn> {
            override fun onResponse(call: Call<UsuarioReturn>, response: Response<UsuarioReturn>) {
                if (response.body() != null) {
                    val registrarAPI = response.body()!!
                    finished(registrarAPI)
                }
            }
            override fun onFailure(call: Call<UsuarioReturn>, t: Throwable) { }
        })
    }
    fun auth(usuario: UsuarioLogar, finished: (usuarioret: UsuarioReturn) -> Unit) {
        service.auth(usuario).enqueue(object : Callback<UsuarioReturn> {
            override fun onResponse(call: Call<UsuarioReturn>, response: Response<UsuarioReturn>) {
                if (response.body() != null) {
                    finished(response.body()!!)
                }
            }
            override fun onFailure(call: Call<UsuarioReturn>, t: Throwable) { }

        })
    }
}