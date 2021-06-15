package com.paula.ana.supertriviasimulator.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.paula.ana.supertriviasimulator.R
import com.paula.ana.supertriviasimulator.dao.CategoriaDAO
import com.paula.ana.supertriviasimulator.dao.RankingDAO
import com.paula.ana.supertriviasimulator.models.Categoria.Categoria
import com.paula.ana.supertriviasimulator.models.Categoria.CategoriaReturn
import com.paula.ana.supertriviasimulator.models.Ranking.Ranking
import com.paula.ana.supertriviasimulator.models.Ranking.RankingReturn
import kotlinx.android.synthetic.main.item_categoria.view.*
import kotlinx.android.synthetic.main.item_categoria.view.lbName
import kotlinx.android.synthetic.main.item_pessoa.view.*

class RankingAdapter():  RecyclerView.Adapter<RankingAdapter.ViewHolder>() {
    private val dao = RankingDAO()

    private var rankingRet: RankingReturn? = null;

    private var ranking = mutableListOf<Ranking>()


    init {
      dao.getAll { rankingRetorno ->
          ranking = rankingRetorno.data?.ranking.toMutableList();
          notifyDataSetChanged()
        }
    }

    override fun getItemCount() = ranking.size


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(
                    LayoutInflater
                            .from(parent.context)
                            .inflate(R.layout.item_pessoa, parent, false)
            )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val ranking = ranking[position]
        holder.fillView(ranking)
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            fun fillView(ranking: Ranking){

                itemView.lbName.text = ranking.user;
                itemView.lbScore.text = ranking.score;
        }
    }
}