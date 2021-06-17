package com.paula.ana.supertriviasimulator.dao

import android.util.Log
import com.paula.ana.supertriviasimulator.models.Jogo.GameReturn
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.QuestaoReturn
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.ResultadoReturn
import com.paula.ana.supertriviasimulator.models.Ranking.RankingReturn
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioLogar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioRegistrar
import com.paula.ana.supertriviasimulator.models.Usuario.UsuarioReturn
import com.paula.ana.supertriviasimulator.networks.services.GameService
import com.paula.ana.supertriviasimulator.networks.services.UsuarioService
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class GameDAO {
    val retrofit = Retrofit.Builder().baseUrl("https://super-trivia-server.herokuapp.com/").addConverterFactory(GsonConverterFactory.create()).build();

    val service = retrofit.create(GameService::class.java);


    fun iniciarJogo(difficulty : String , category_id : String , authorization : String, finished: (gameReturn: GameReturn) -> Unit) {
        service.iniciarJogo(difficulty, category_id, authorization).enqueue(object : Callback<GameReturn> {

            override fun onResponse(call: Call<GameReturn>, response: Response<GameReturn>) {
                finished(response.body()!!)
            }
            override fun onFailure(call: Call<GameReturn>, t: Throwable) {}
        })
    }

    fun finalizarJogo(authorization : String ,finished: (gameReturn: GameReturn) -> Unit) {
        service.finalizarJogo(authorization).enqueue(object : Callback<GameReturn> {

            override fun onResponse(call: Call<GameReturn>, response: Response<GameReturn>) {
                if(response.isSuccessful) {
                    finished(response.body()!!)
                } else {
                    finished(GameReturn("error","" , null))
                }
            }
            override fun onFailure(call: Call<GameReturn>, t: Throwable) {}
        })
    }

    fun verQuestao(authorization:String, finished: (QuestaoReturn: QuestaoReturn) -> Unit) {
        service.verQuestao(authorization).enqueue(object : Callback<QuestaoReturn> {
            override fun onResponse(call: Call<QuestaoReturn>, response: Response<QuestaoReturn>) {
                if(response.isSuccessful) {
                    finished(response.body()!!)
                } else {
                    finished(QuestaoReturn("error", "error",null))
                }
            }
            override fun onFailure(call: Call<QuestaoReturn>, t: Throwable) {
                Log.i("TestandoGame", "entro aqui2 "+t)}
        })
    }
    fun pedirQuestao(authorization:String, finished: (QuestaoReturn: QuestaoReturn) -> Unit) {
        service.pedirQuestao(authorization).enqueue(object : Callback<QuestaoReturn> {

            override fun onResponse(call: Call<QuestaoReturn>, response: Response<QuestaoReturn>) {
                Log.i("Teste", "Response status GameDAO pedirQuestao: "+ response.body()?.status)
                if (response.body() != null) {
                    val registrarAPI = response.body()!!
                    finished(registrarAPI)
                }
            }
            override fun onFailure(call: Call<QuestaoReturn>, t: Throwable) {}
        })
    }
    fun responderQuestao(authorization:String, resposta: String, finished: (ResultadoReturn: ResultadoReturn) -> Unit) {
        service.responderQuestao(resposta,authorization).enqueue(object : Callback<ResultadoReturn> {

            override fun onResponse(call: Call<ResultadoReturn>, response: Response<ResultadoReturn>) {
                Log.i("Teste", "Response status gameDAO responderQuestao: "+ response.body()?.status)
                if (response.body() != null) {
                    val registrarAPI = response.body()!!
                    finished(registrarAPI)
                }
            }
            override fun onFailure(call: Call<ResultadoReturn>, t: Throwable) {}
        })
    }
}