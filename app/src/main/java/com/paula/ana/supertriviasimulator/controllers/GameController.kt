package com.paula.ana.supertriviasimulator.controllers

import android.app.Application
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria

class GameController: Application() {
    // é um public static
    companion object {
        var difficult = ""
        var category = Categoria("0","")
        var problem = false

    }

}