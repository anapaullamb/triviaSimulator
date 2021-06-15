package com.paula.ana.supertriviasimulator.networks.services
import retrofit2.Call
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioLogar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioRegistrar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioReturn
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface UsuarioService {
    @POST("users")
    @Headers("Content-Type: application/json")
    fun insert(@Body usuario: UsuarioRegistrar): Call<UsuarioReturn>

    @POST("auth")
    @Headers("Content-Type: application/json")
    fun auth(@Body usuario: UsuarioLogar): Call<UsuarioReturn>
}