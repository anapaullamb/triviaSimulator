package com.paula.ana.supertriviasimulator.fragments

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.controllers.GameController
import com.paula.ana.supertriviasimulator.dao.GameDAO
import com.paula.ana.supertriviasimulator.models.Jogo.Game
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.Questao
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.QuestaoData
import com.paula.ana.supertriviasimulator.models.Jogo.Questao.RespostaCorreta
import kotlinx.android.synthetic.main.fragment_game.view.*

class GameFragment : Fragment() {

    val dao = GameDAO();
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_game, container, false)
        val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)

        view.tvScoore.text = "Score: "
        val token = sharedPref?.getString("token", null);
        if(token != null){
            if (GameController.problem) {
                dao.verQuestao(token) { questao ->
                    if(questao.status == "success"){
                        setJogo(questao.data!!.problem, view)
                    }
                }
            } else {
                dao.pedirQuestao(token) { questao ->
                    if(questao.status == "success"){
                        setJogo(questao.data!!.problem, view)
                        GameController.problem = true
                    }
                }
            }
        }

        view.btUm.setOnClickListener { responderQuestoes("1", view) }
        view.btDois.setOnClickListener { responderQuestoes("2", view) }
        view.btTres.setOnClickListener { responderQuestoes("3", view) }
        view.btQuatro.setOnClickListener { responderQuestoes("4", view) }

        return view
    }

    private fun setJogo(questao: Questao, view: View){
        view.tvCategoria.text = questao.categoria.name
        view.tvDificuldade.text = questao.dificuldade
        view.tvPergunta.text = questao.pergunta

        view.btUm.text = questao.respostas[0].description
        view.btDois.text = questao.respostas[1].description
        view.btTres.text = questao.respostas[2].description
        view.btQuatro.text = questao.respostas[3].description

    }
    private fun responderQuestoes(resposta: String, view: View){
        val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", null);
       if (token != null) {
            dao.responderQuestao(token,resposta){resultado ->
                var Title: Int;
                if(resposta == resultado.data.resultado.resposta!!.order){
                    Title = R.string.won;
                }else{
                    Title = R.string.missed;
                }
                // Initialize a new instance of
                view.tvScoore.text = getString(R.string.et_score)+": "+resultado.data!!.resultado.score
                criarAlert( Title, view)
            }
        }
    }
    private fun criarAlert( title: Int, view: View){
        val sharedPref = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)
        val token = sharedPref?.getString("token", null);
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(title)

        builder.setMessage(R.string.newQuestion)
        builder.setPositiveButton(R.string.yes){dialog, which ->
            if (token != null) {
                dao.pedirQuestao(token) { questao ->
                    if(questao.status == "success"){
                        setJogo(questao.data!!.problem, view)
                    }
                }
            }
        }
        builder.setNegativeButton(R.string.endGame){dialog,which ->
            findNavController().navigate(R.id.navigateToMenuGame)

        }
        builder.setCancelable(false);
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}