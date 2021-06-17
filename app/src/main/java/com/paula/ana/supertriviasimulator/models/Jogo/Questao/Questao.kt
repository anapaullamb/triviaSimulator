package com.paula.ana.supertriviasimulator.models.Jogo.Questao

import com.google.gson.annotations.SerializedName
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria


data class Questao (
    var id: String,
    @SerializedName("question") var pergunta: String,
    @SerializedName("difficulty") var dificuldade: String,
    @SerializedName("category") var categoria: Categoria,
    @SerializedName("answers") var respostas: List<Resposta>
)
