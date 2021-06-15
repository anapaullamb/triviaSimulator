package com.paula.ana.supertriviasimulator.models.Usuario

data class Usuario (
        var email: String,
        var password: String,
        var name: String?,
        var token: String?
){
    var id: Long? = null
}