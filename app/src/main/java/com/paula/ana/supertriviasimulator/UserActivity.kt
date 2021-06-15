package com.paula.ana.supertriviasimulator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.paula.ana.supertriviasimulator.dao.GameDAO
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    val dao = GameDAO();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)
        bottomNavigationView.setupWithNavController(findNavController(R.id.navHostFragment))
    }
}