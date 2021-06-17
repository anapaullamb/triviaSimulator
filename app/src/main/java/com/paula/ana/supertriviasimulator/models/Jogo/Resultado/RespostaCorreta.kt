package com.paula.ana.supertriviasimulator.models.Jogo.Questao

import com.google.gson.annotations.SerializedName
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria


data class RespostaCorreta (
    var order: String,
    @SerializedName("description") var descricao: String
)
