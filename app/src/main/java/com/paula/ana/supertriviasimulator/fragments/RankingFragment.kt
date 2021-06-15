package com.paula.ana.supertriviasimulator.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.adapters.CategoriaAdapter
import com.paula.ana.supertriviasimulator.adapters.RankingAdapter
import kotlinx.android.synthetic.main.fragment_ranking.view.*

class RankingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view =  inflater.inflate(R.layout.fragment_ranking, container, false)


        val recyclerView = view.ranking;
        recyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = RankingAdapter()




        return view;
    }

}