package com.paula.ana.supertriviasimulator.models.Jogo


data class Game (
    var creation: String,
    var started_at: String,
    var finished_at: String?,
    var score: String,
    var status: String
)
