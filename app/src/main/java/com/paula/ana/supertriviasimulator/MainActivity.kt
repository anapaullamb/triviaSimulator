package com.paula.ana.supertriviasimulator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.paula.ana.supertriviasimulator.dao.UsuarioDAO

class MainActivity : AppCompatActivity() {
    private val dao = UsuarioDAO()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}