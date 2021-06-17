package com.paula.ana.supertriviasimulator.models.Usuario

data class UsuarioData (
        var user: User
)
data class User(

        var email: String,
        var token: String,
        var name: String
)