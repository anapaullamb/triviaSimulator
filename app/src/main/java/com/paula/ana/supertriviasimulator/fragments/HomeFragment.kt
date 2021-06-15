 package com.paula.ana.supertriviasimulator.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paula.ana.supertriviasimulator.MainActivity
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.UserActivity
import com.paula.ana.supertriviasimulator.adapters.CategoriaAdapter
import com.paula.ana.supertriviasimulator.controllers.GameController
import com.paula.ana.supertriviasimulator.dao.GameDAO
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria
import kotlinx.android.synthetic.main.fragment_game_menu.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*

 class HomeFragment : Fragment() {

     val dao = GameDAO();
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)

        val nome = sharedPref?.getString("nome", null)

        GameController.difficult = ""
        GameController.category = Categoria("0","")

        view.tvName.text = nome
        val recyclerView = view.categorias;
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = CategoriaAdapter()

        view.bt_play.setOnClickListener { iniciarJogo() }
        view.bt_logoff.setOnClickListener {  deslogar()}

        view.tv_facil.setTextColor(Color.GRAY)
        view.tv_dificil.setTextColor(Color.GRAY)
        view.tv_medio.setTextColor(Color.GRAY)

        view.bt_facil.setOnClickListener {
            GameController.difficult = "easy"
            trocarCor(view.tv_dificil,view.tv_medio,view.tv_facil)
        }
        view.bt_medio.setOnClickListener {
            trocarCor(view.tv_facil,view.tv_dificil,view.tv_medio)
            GameController.difficult = "medium"

        }
        view.bt_dificil.setOnClickListener {
            GameController.difficult = "hard"
            trocarCor(view.tv_facil,view.tv_medio,view.tv_dificil)
            view.tv_medio
        }

        val token = sharedPref?.getString("token", null);
        if (token != null) {
            dao.verQuestao(token) { questao ->
                if(questao.status == "success"){
                    GameController.problem= true;
                    findNavController().navigate(R.id.navigateToGame)
                } else {
                    dao.finalizarJogo (token){}
                }
            }
        }
        return view;
    }
     private fun iniciarJogo(){
         val dificuldade = GameController.difficult
         val categoria = GameController.category.id
         val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)
         val token = sharedPref?.getString("token", null);
         if (token != null) {
             dao.iniciarJogo(dificuldade, categoria,token) { }
             GameController.problem= false;
             findNavController().navigate(R.id.navigateToGame)
         }
     }
     private fun trocarCor(textoUm :TextView, textoDois: TextView, textoTres: TextView){
         textoUm.setTextColor(Color.GRAY)
         textoDois.setTextColor(Color.GRAY)
         textoTres.setTextColor(Color.BLACK)

     }
     private fun deslogar(){

         val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)
         if (sharedPref != null) {
             with(sharedPref.edit()) {
                 remove("token")
                 remove("nome")
                 commit()
             }
         }
         val intent = Intent(activity, MainActivity::class.java)
         startActivity(intent)
     }
}
