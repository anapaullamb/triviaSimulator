package com.paula.ana.supertriviasimulator.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.controllers.GameController
import com.paula.ana.supertriviasimulator.dao.GameDAO
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria
import kotlinx.android.synthetic.main.fragment_game_menu.view.*

class GameMenuFragment : Fragment() {

    val dao = GameDAO();
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_game_menu, container, false)
        var token = activity?.getSharedPreferences("arq_simulator_trivia", Context.MODE_PRIVATE)?.getString("token", null);
        view.bt_close.setOnClickListener { findNavController().navigate(R.id.navigateToHome2) }
        if (token != null) {
            dao.finalizarJogo (token){ result ->
                view.tvInicio.text = getString(R.string.started_art) + ": "+result.data?.game?.started_at
                view.tvFim.text = getString(R.string.finished_at) + ": "+result.data?.game?.finished_at
                view.tvScore.text = getString(R.string.et_score) + ": "+result.data?.game?.score
            }
        }
        return view;
    }
}