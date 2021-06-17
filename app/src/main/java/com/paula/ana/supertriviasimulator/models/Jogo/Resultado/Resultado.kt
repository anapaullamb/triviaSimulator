package com.paula.ana.supertriviasimulator.models.Jogo.Questao

import com.google.gson.annotations.SerializedName
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria


data class Resultado (
    var status: String,
    var score: String,
    @SerializedName("correct_answer") var resposta: RespostaCorreta?
)
