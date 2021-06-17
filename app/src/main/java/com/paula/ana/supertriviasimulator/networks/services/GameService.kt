package com.paula.ana.supertriviasimulator.networks.services
import com.paula.ana.supertriviasimulator.models.Jogo.GameReturn
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.QuestaoReturn
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.ResultadoReturn
import retrofit2.Call
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioLogar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioRegistrar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioReturn
import retrofit2.http.*

interface GameService {
    @GET("games")
    @Headers("Content-Type: application/json")
    fun iniciarJogo(@Query("difficulty") difficulty: String, @Query("category_id") category_id: String, @Header("Authorization") authorization: String): Call<GameReturn>

    @DELETE("games")
    @Headers("Content-Type: application/json")
    fun finalizarJogo(@Header("Authorization") authorization: String): Call<GameReturn>



    @GET("problems/view")
    @Headers("Content-Type: application/json")
    fun verQuestao(@Header("Authorization") authorization: String): Call<QuestaoReturn>

    @GET("problems/next")
    @Headers("Content-Type: application/json")
    fun pedirQuestao(@Header("Authorization") authorization: String): Call<QuestaoReturn>

    @POST("problems/answer?")
    @Headers("Content-Type: application/json")
    fun responderQuestao(@Query("answer" ) resposta: String, @Header("Authorization") authorization: String): Call<ResultadoReturn>
}